package com.example.istima.utils

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class Global : Application() {

    companion object {
        @JvmField
//        var connectedDevice: Map<String, String> = mapOf("" to "")
//        var connectedBluetoothDevice: BluetoothDevice? = null

        var googleAccount: GoogleSignInAccount? = null
        var signedIn: Boolean = false

        const val SuccessStatus: String = "OK"
        var anErrorCode: Int = 1

        var sharedPreferencesName: String = "istima_preferences"

        var reports = ArrayList<String>()

    }
}