package com.example.istima.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.istima.R
import com.example.istima.services.AuthService
import com.example.istima.services.SharedPreferencesService
import com.example.istima.ui.theme.KplcDarkGreen
import com.example.istima.ui.theme.Red
import com.example.istima.utils.Routes
import com.example.istima.views.auth.pagePadding

@Composable
fun ProfilePage(navController: NavHostController) {
    val profilePicSize = 200.dp

    var imageBitmap: Painter = painterResource(id = R.drawable.person_icon)
//    var launcherIcon: Painter = painterResource(id = R.mipmap.ic_launcher)
    val context = LocalContext.current

    val authService = AuthService(context)

    val sharedPreferencesService = SharedPreferencesService(context)

    Column(
        modifier = Modifier
            .padding(pagePadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .height(profilePicSize)
                .width(profilePicSize)
                //            .size(profilePicSize)
                .clip(shape = CircleShape)
                .background(color = MaterialTheme.colorScheme.primary)
                .clip(shape = CircleShape),
        ) {
                    Image(
                        painter = imageBitmap,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(profilePicSize),
                    )
        }
        sharedPreferencesService.getUserName()?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelSmall,
    //            color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 30.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ) {
//            Icon(
//                painter = launcherIcon,
//                contentDescription = "",
//                modifier = Modifier
//                    .size(30.dp)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(text = "Reports:")
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(text = "20")
//        }
        Button(
            onClick = {
                sharedPreferencesService.deleteEverything()
                authService.signOut()
//                val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
//                activityManager?.clearApplicationUserData()
//                val processId =
//                Process.killProcess(processId)

                /**
                 * Steve Muindi's implementation
                 */
                navController.navigate("login")

            },
            modifier = Modifier
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Red,
                contentColor = Color.White
            )
        ) {
            Text(text = "Logout")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfilePagePreview() {
    var context  = LocalContext.current
    var navController = NavHostController(context)
    ProfilePage(navController)
}