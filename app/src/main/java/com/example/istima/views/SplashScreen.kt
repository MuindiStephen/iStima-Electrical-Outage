package com.example.istima.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.util.Log
import androidx.navigation.NavController

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.istima.R
import com.example.istima.utils.Global
import com.example.istima.views.auth.pagePadding

@Composable
fun SplashScreen(navController: NavController?) {

    val context = LocalContext.current
//    var launcherIcon = painterResource(id = R.mipmap.ic_launcher)

    val sharedPrefs: SharedPreferences = context.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)
//
    var startPage = ""
    val alpha = 0.6f

    if (sharedPrefs.getString("userEmail", "null") == "null") {
        startPage = "login"
    } else {
        Log.d("ABC", "MAIN TO LOAD")
        startPage = "main"
    }
    Handler().postDelayed({
        navController!!.navigate(startPage)
    }, 4000)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "iStima",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .graphicsLayer(alpha = alpha),
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(pagePadding))

//            Image(
//                painter = launcherIcon,
//                contentDescription = null,
//                modifier = Modifier
//                    .size(96.dp)
//                    .graphicsLayer(alpha = alpha),
//                contentScale = ContentScale.Fit
//            )

            Spacer(modifier = Modifier.height(pagePadding))

            Text(
                text = "Welcome to iStima",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .graphicsLayer(alpha = alpha),
                fontSize = 28.sp,
                fontWeight = FontWeight.W900

            )

            Spacer(modifier = Modifier.height(pagePadding))

            Text(
                text = "Report electrical outages",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .graphicsLayer(alpha = alpha),
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()

    SplashScreen(navController)
}
