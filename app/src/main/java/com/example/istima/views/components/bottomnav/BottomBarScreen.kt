package com.example.istima.views.components.bottomnav

import com.example.istima.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {

    object Feed: BottomBarScreen(
        route = "feed",
        title = "Feed",
        icon = R.drawable.home_icon
    )

    object Report: BottomBarScreen(
        route = "report",
        title = "Report",
        icon = R.mipmap.add_icon_foreground
    )

    object Profile: BottomBarScreen(
        route = "profile",
        title = "profile",
        icon = R.mipmap.person_icon_foreground
    )

    object Map: BottomBarScreen(
        route = "map",
        title = "map",
        icon = R.drawable.map_icon
    )
}
