package com.example.istima.utils

sealed class Routes(val route: String) {

    object LoginPage: Routes("login")
    object RegisterPage: Routes("register")
    object MainPage: Routes("main")
    object FeedPage: Routes("feed")
    object NewReport: Routes("newReport")
//    object FeedPage: Routes("feed") {
//        fun createRoute(macAddress: String) = "chat/$macAddress"
//    }
}