package com.example.istima.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.istima.views.components.bottomnav.BottomBar
import com.example.istima.views.components.bottomnav.BottomNavGraph

@Composable
fun MainPage(navController: NavHostController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = bottomNavController) }
    ) {
        Modifier.padding(it)
        BottomNavGraph(
            navController = bottomNavController
        )
    }
}