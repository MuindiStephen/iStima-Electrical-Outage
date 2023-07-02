package com.example.istima.services

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.example.istima.utils.Global
import org.json.JSONObject
import java.util.Locale

class MapService {
    fun getNameFromCoordinates(
        context: Context, lat: Double, long: Double
    ): String {
        var cityName: String?
        val geoCoder = Geocoder(context, Locale.getDefault())
        val address = geoCoder.getFromLocation(lat,long,1)

        Log.i("ABC", "address: $address")

        cityName = address?.get(0)!!.adminArea
        if (cityName == null){
            cityName = address[0]!!.locality
            if (cityName == null){
                cityName = address[0]!!.subAdminArea
            }
        }
        return cityName
    }

    fun extractCoordinatesFromArrayList(arrayList: ArrayList<String>): ArrayList<Pair<Double, Double>> {
        val coordinatesList = ArrayList<Pair<Double, Double>>()

        for (report in arrayList) {
            val jsonObject = JSONObject(report)

            val latitude = jsonObject.getString("latitude").toDoubleOrNull()
            val longitude = jsonObject.getString("longitude").toDoubleOrNull()

            if (latitude != null && longitude != null) {
                val coordinates = Pair(latitude, longitude)
                coordinatesList.add(coordinates)
            }
        }

        Global.coordinatesList = coordinatesList

        return coordinatesList
    }
}