package com.example.istima.views

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.istima.ui.theme.KplcDarkGreen
import com.example.istima.ui.theme.LightRed
import com.example.istima.ui.theme.WhiteSmoke
import com.example.istima.utils.Global
import com.example.istima.views.auth.pagePadding
import org.json.JSONArray

@Composable
fun FeedPage(navController: NavController) {
    val cornerShape = 1.dp
    val pagePadding = 20.dp
    val elementHeight = 60.dp

    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)

    val firebaseFirestoreService = FirebaseFirestoreService()

    var email by remember { mutableStateOf("") }
    var reports by remember{ mutableStateOf(Global.reports) }
//    Log.d("ABC", "reports : ${reports.size}")

//    Handler().postDelayed({

        Log.d("ABC", "GlobalReports = ${Global.reports}")
        reports = Global.reports
        Log.d("ABC", "reports : ${reports.size}")
//    }, 3000)

    Column(
        modifier = Modifier
            .fillMaxHeight()
//            .verticalScroll(rememberScrollState())
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
//            GoogleMap(
//                modifier = Modifier.fillMaxSize()
//            )
        }
        Spacer(modifier = Modifier.height(pagePadding / 2))
        Text("${reports.size}")
//        LazyColumn(
//            state = rememberLazyListState()
//        ) {
//            items(reports) { report ->
//                val jsonArray = JSONArray(report)
//                Log.d("ABC", "kjkj")
//
//                for (i in 0 until jsonArray.length()) {
//                    val jsonObject = jsonArray.getJSONObject(i)
//
//                    val name = jsonObject.getString("userName")
//                    val date = jsonObject.getString("date")
//                    val time = jsonObject.getString("time")
//
////                    PostCard(name = name, date = date, time = time, )
//                    Text("${reports.size}")
//                }
//            }
//        }
    }
}

@Composable
fun PostCard(name: String, date: String, time: String) {

    val locationIcon = painterResource(id = R.mipmap.location_icon)

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
                    " Marakaru, Bungoma",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
            Spacer(Modifier.height(pagePadding / 2))
            Text(
                "There has be no power for 40 hours. "
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
