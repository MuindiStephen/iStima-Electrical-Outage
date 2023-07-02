//package com.example.istima.services
//
//import android.content.Context
//import android.location.Geocoder
//import java.util.Locale
//
//class MapService {
//    fun getNameFromCoordinates(
//        context: Context, lat: Double, long: Double
//    ): String {
//        var cityName: String?
//        val geoCoder = Geocoder(context, Locale.getDefault())
//        val address = geoCoder.getFromLocation(lat,long,1)
//
//        cityName = address[0]!!.adminArea
//        if (cityName == null){
//            cityName = address[0]!!.locality
//            if (cityName == null){
//                cityName = address[0]!!.subAdminArea
//            }
//        }
//        return cityName
//    }
//}