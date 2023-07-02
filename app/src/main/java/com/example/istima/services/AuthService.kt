package com.example.istima.services

import android.content.Context
import com.example.istima.utils.Global
import com.google.firebase.auth.FirebaseAuth

class AuthService(private var context: Context) {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseFirestoreService = FirebaseFirestoreService(context)

    fun validateCredentials(newUser: Boolean = false, email: String, password: String, confirmPassword: String = ""): String {
        if (email.isBlank() || password.isBlank()) {
            return "Email and Password can't be blank"
        }

        if (newUser) {
            if (password != confirmPassword) {
                return "Password and Confirm Password do not match"
            }
        }

        return Global.SuccessStatus
    }

    fun signIn(email: String, password: String): String {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val sharedPreferences = context.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString(Global.sharedPreferencesUserId, user?.uid)
//                    editor.putString(Global.sharedPreferencesUserName, firebaseFirestoreService.getUserName(user?.uid!!))
                    editor.apply()
                } else {
                    return@addOnCompleteListener
                }
            }
        return Global.SuccessStatus
    }

    fun signUp(email: String, password: String, userName: String): String {
        mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val sharedPreferences = context.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString(Global.sharedPreferencesUserId, user?.uid)
//                    editor.putString(Global.sharedPreferencesUserName, firebaseFirestoreService.getUserName(user?.uid!!))
                    editor.apply()
                } else {
                    return@addOnCompleteListener
                }
            }
        return Global.SuccessStatus
    }
}
