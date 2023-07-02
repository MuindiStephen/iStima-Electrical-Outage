package com.example.istima.views.components.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.istima.views.FeedPage
import com.example.istima.views.maps.MapFeed
import com.example.istima.views.NewReport
import com.example.istima.views.ProfilePage

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Feed.route
    ) {
        composable(route = BottomBarScreen.Feed.route) {
            FeedPage(navController)
        }
        composable(route = BottomBarScreen.Report.route) {
            NewReport(navController)
        }
        composable(route = BottomBarScreen.Map.route) {
            MapFeed(navController)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfilePage()
        }
    }
}