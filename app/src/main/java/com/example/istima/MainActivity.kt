package com.example.istima


import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.Manifest
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.istima.utils.Routes
import com.example.istima.ui.theme.IStimaTheme
import com.example.istima.views.FeedPage
import com.example.istima.views.auth.LoginPage
import com.example.istima.views.MainPage
import com.example.istima.views.NewReport
import com.example.istima.views.auth.RegisterPage
import com.example.istima.views.SplashScreen
import com.google.firebase.auth.FirebaseAuth

const val PREFS_FILE_NAME = "MyAppPrefs"
const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1234

class MainActivity : ComponentActivity() {

    private lateinit var mAuth: FirebaseAuth
    private var locationPermissionGranted = mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPrefs: SharedPreferences = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        val isFirstLaunch = sharedPrefs.getBoolean("isFirstLaunch", true)

        var startPage: String = "login"
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser


        if (isFirstLaunch) {
            startPage = "splash"
            val editor: SharedPreferences.Editor = sharedPrefs.edit()
            editor.putBoolean("isFirstLaunch", false)
            editor.apply()
        } else {
            Handler().postDelayed({
                startPage = if (user == null) {
                    "login"
                } else {
                    "main"
                }
            }, 3000)
        }

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
                    NavigationAppHost(navController = navController, startDestination = startPage)
                }
            }
        }
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted.value = true//we already have the permission
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            locationPermissionGranted.value=true
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationAppHost(navController: NavHostController, startDestination: String = "login") {
    val ctx = LocalContext.current

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.LoginPage.route) { LoginPage(navController) }
        composable(Routes.RegisterPage.route) { RegisterPage(navController) }
        composable(Routes.MainPage.route) { MainPage(navController) }
        composable(Routes.FeedPage.route) { FeedPage(navController) }
        composable(Routes.NewReport.route) { NewReport(navController) }
        composable(Routes.SplashScreen.route) { SplashScreen(navController) }
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
