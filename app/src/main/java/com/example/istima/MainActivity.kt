package com.example.istima

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.istima.utils.Routes
import com.example.istima.ui.theme.IStimaTheme
import com.example.istima.views.FeedPage
import com.example.istima.views.LoginPage
import com.example.istima.views.MainPage
import com.example.istima.views.NewReport
import com.example.istima.views.RegisterPage

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IStimaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    LoginPage()
//                    FeedPage()
//                    RegisterPage()
                    val navController = rememberNavController()
                    NavigationAppHost(navController = navController)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationAppHost(navController: NavHostController) {
    val ctx = LocalContext.current

    NavHost(navController = navController, startDestination = "login") {
        composable(Routes.LoginPage.route) { LoginPage(navController) }
        composable(Routes.RegisterPage.route) { RegisterPage(navController) }
        composable(Routes.MainPage.route) { MainPage(navController) }
        composable(Routes.FeedPage.route) { FeedPage(navController) }
        composable(Routes.NewReport.route) { NewReport(navController) }
//        composable(Routes.ChatPage.route) { navBackStackEntry ->
//            val macAddress = navBackStackEntry.arguments?.getString("macAddress")
//            if (macAddress == null) {
//                Toast.makeText(ctx, "Address not provided", Toast.LENGTH_SHORT).show()
//            } else {
//                ChatPage(navController = navController, macAddress = macAddress)
//            }
//        }
    }
}
