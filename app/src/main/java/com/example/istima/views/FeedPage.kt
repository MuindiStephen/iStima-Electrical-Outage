package com.example.istima.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.istima.R
import com.example.istima.services.FirebaseFirestoreService
import com.example.istima.services.MapService
import com.example.istima.ui.theme.KplcDarkGreen
import com.example.istima.ui.theme.LightRed
import com.example.istima.ui.theme.WhiteSmoke
import com.example.istima.utils.Global
import com.example.istima.views.auth.pagePadding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import org.json.JSONObject

@Composable
fun FeedPage(navController: NavController) {
    val cornerShape = 1.dp
    val pagePadding = 20.dp
    val elementHeight = 60.dp

    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)

    val firebaseFirestoreService = FirebaseFirestoreService(context)

    var email by remember { mutableStateOf("") }
    var reports by remember{ mutableStateOf(Global.reports) }

    val myLatitude = 0.6184071
    val myLongitude = 34.5242516

    var coordinates by remember { mutableStateOf(Global.coordinatesList) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(LightRed)
            .padding(20.dp)
    ) {
        Text(
            "Feed",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(pagePadding / 2))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = KplcDarkGreen)
                .clip(shape = RoundedCornerShape(cornerShape))
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                onMapClick = {
                    navController.navigate("map")
                }
            ) {

                Marker(
                    state = rememberMarkerState(position = LatLng(myLatitude, myLongitude)),
                    title = "Marker1",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                )
                coordinates.forEach { coordinate ->
                    val latitude = coordinate.first
                    val longitude = coordinate.second

                    Marker(
                        state = rememberMarkerState(position = LatLng(latitude, longitude)),
                        title = "Marker1",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(pagePadding / 2))
//        Text("${reports.size}")
        LazyColumn(
            state = rememberLazyListState()
        ) {
            items(reports) { report ->

                val jsonObject = JSONObject(report)

                val name = jsonObject.getString("userName")
                val description = jsonObject.getString("description")
                val uid = jsonObject.getString("userID")
                val date = jsonObject.getString("date")
                val time = jsonObject.getString("time")
                val latitude = jsonObject.getString("latitude")
                val longitude = jsonObject.getString("longitude")

                PostCard(
                    name = name, date = date, time = time,
                    description = description, latitude = latitude.toDouble(),
                    longitude = longitude.toDouble())
            }
        }
        Spacer(modifier = Modifier.height(elementHeight).padding(elementHeight))
    }
}

@Composable
fun PostCard(
    name: String, date: String,
    time: String, description: String = "",
    latitude: Double, longitude: Double
) {

    val context = LocalContext.current
    val locationIcon = painterResource(id = R.mipmap.location_icon)

    var mapService = MapService()
    Box(
        modifier = Modifier
//            .clip(RoundedCornerShape(5.dp))
            .fillMaxWidth()
            .background(WhiteSmoke)
            .padding(top = 10.dp)
            .padding(horizontal = 10.dp)
    ) {
        Column {
            Text(
                name,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Row {
                Text(
                    "$date  · $time",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
                Text(
                    " · ",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
                Icon(
                    painter = locationIcon,
                    contentDescription = null,
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .padding(0.dp)
                        .size(13.dp)
                )
                Text(
                    //mapService.getNameFromCoordinates(context = context, lat = latitude, long = longitude),
                    // "${mapService.getNameFromCoordinates(context,latitude,longitude)}",
                    "$latitude $longitude",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
            Spacer(Modifier.height(pagePadding / 2))
            Text(
                description
            )
            Spacer(Modifier.height(pagePadding))
            Divider(
                color = Color.LightGray,
                thickness = 0.55.dp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FeedPagePreview() {
    var ctx = LocalContext.current
    var navController: NavHostController = NavHostController(ctx)
    FeedPage(navController)
}
