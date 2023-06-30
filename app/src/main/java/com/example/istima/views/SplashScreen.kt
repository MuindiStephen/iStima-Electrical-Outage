package com.example.istima.views

import android.os.Handler
import androidx.navigation.NavController

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.istima.R
import com.example.istima.views.auth.pagePadding

@Composable
fun SplashScreen(navController: NavController?) {
//    val alpha: Float by animateFloatAsState(
//        targetValue = if (LifecycleOwner.current.lifecycle.currentState == Lifecycle.State.RESUMED) 1f else 0f
//    )

    val alpha = 0.6f

//    val launcherIcon: Painter = painterResource(id = R.mipmap.ic_launcher)

    Handler().postDelayed({
        navController!!.navigate("login")
    }, 2000)

    Box(
        modifier = Modifier.fillMaxSize(),
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
                    .graphicsLayer(alpha = alpha)
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
                text = "Report electrical outages",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .graphicsLayer(alpha = alpha)
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
