package com.example.istima.views.maps

import android.content.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MapFeed(navController: NavHostController) {
    var message = ""
    mapUI()
}

@Composable
fun mapUI() {
    val mapView = rememberMapViewWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        // on below line adding a map view to it.
        AndroidView({ mapView }) { mapView ->
            // on below line launching our map view
            CoroutineScope(Dispatchers.Main).launch {
                val map = mapView.awaitMap()
                // on below line adding zoom controls for map.
                map.uiSettings.isZoomControlsEnabled = true
                // on below line we are creating a lat lng
                // variable for sydney location
                val sydney = LatLng(-35.016, 143.321)
                // on below line adding a marker to map and
                // specifying tile and position for it.
                val markerOptions = MarkerOptions()
                    .title("Sydney")
                    .position(sydney)
                map.addMarker(markerOptions)
            }
        }
    }
}
