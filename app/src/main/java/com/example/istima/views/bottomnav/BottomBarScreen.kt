package com.example.istima.views.bottomnav

import com.example.istima.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {

    // for home
    object Feed: BottomBarScreen(
        route = "feed",
        title = "Feed",
        icon = R.drawable.home_icon
    )

    // for report
    object Report: BottomBarScreen(
        route = "report",
        title = "Report",
        icon = R.mipmap.add_icon_foreground
    )

    // for report
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.mipmap.person_icon_foreground
    )
}
