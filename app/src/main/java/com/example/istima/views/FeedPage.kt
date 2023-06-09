package com.example.istima.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.istima.ui.theme.KplcDarkGreen
import com.example.istima.ui.theme.LightRed
import com.example.istima.ui.theme.WhiteSmoke

@Composable
fun FeedPage(navController: NavController) {
    val cornerShape = 1.dp
    val pagePadding = 20.dp
    val elementHeight = 60.dp

    Column(
        modifier = Modifier
            .background(LightRed)
            .padding(20.dp)
    ) {
        Text(
            "Planned Outages",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(pagePadding / 2))
        Row {
            Box(
                Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .background(Color(0x5D058C54))
                    .padding(5.dp)
            ) {
                Text(
                    "Today",
                    color = KplcDarkGreen,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(pagePadding / 4))
            Box(
                Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .background(Color.LightGray)
                    .padding(5.dp)
            ) {
                Text(
                    "Tomorrow",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(pagePadding/2))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(WhiteSmoke)
                .padding(10.dp)
        ) {
            PlannedOutageItem()
            PlannedOutageItem()
            PlannedOutageItem()
            PlannedOutageItem()
            PlannedOutageItem()
            PlannedOutageItem()
        }
        Spacer(modifier = Modifier.height(pagePadding))
        Text(
            "Feed",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(pagePadding / 2))
        PostCard()
        PostCard()
    }
}

@Composable
fun PostCard() {

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
                "Tabitha Wamaitha",
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Row {
                Text(
                    "3 hours ago",
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
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(13.dp)
                        .padding(0.dp)
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

@Composable
fun PlannedOutageItem() {
    Box {
        Row {
            Text(
                text = "- Kibabii Area, Bungoma "
            )
            Text(
                " · ",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Text(
                text = " 5pm - 7pm "
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
