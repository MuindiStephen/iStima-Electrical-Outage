package com.example.istima.views.maps

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapFeed(navController: NavHostController) {

    var isMapLoaded by remember { mutableStateOf(false) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(44.810058, 20.4617586), 16f)
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        onMapLoaded = {
            isMapLoaded = true
        },
//        modifier = Modifier.matchParentSize()
    ) {
        Marker(
            state = rememberMarkerState(position = LatLng(44.811058, 20.4617586)),
            title = "Marker1",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        )
    }
    if (!isMapLoaded) {
        AnimatedVisibility(
//            modifier = Modifier
//                .matchParentSize(),
            visible = !isMapLoaded,
            enter = EnterTransition.None,
            exit = fadeOut()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
            )
        }
    }
}

@Preview
@Composable
fun preview() {
    var localContext = LocalContext.current
    var navHostController = NavHostController(localContext)

    MapFeed(navHostController)
}
