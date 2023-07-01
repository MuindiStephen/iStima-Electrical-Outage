package com.example.istima.services

import android.content.Context
import com.example.istima.utils.Global

class AuthService(private var context: Context) {
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
}