package com.example.istima.data

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

const val Req_Code:Int=123

class FirebaseAuthHelper(
    private var context: Context
) {
    var mFirebaseAuth = FirebaseAuth.getInstance()
    var myApiKey = "AIzaSyAnyofaYhJmQnH5N51kglBWOPf_Kc9iNug"
    val googleSignInOptions: GoogleSignInOptions = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(myApiKey)
        .requestEmail()
        .build()

    //         mySignInClient = GoogleSignInOptions
    var mGoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)


    private  fun signInGoogle(){
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
//        startActivityForResult(context as Activity, signInIntent)
    }
}