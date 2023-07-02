package com.example.istima.services

import android.content.Context
import com.example.istima.utils.Global

class SharedPreferencesService(
    private var context: Context
) {

    val sharedPreferences = context.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)

    val editor = sharedPreferences.edit()

    fun addUserDetailsAfterSignUp() {

    }

    fun getUserName(): String? {
        return sharedPreferences.getString(Global.sharedPreferencesUserName, null)
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(Global.sharedPreferencesUserId, null)
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(Global.sharedPreferencesUserEmail, null)
    }

    fun deleteEverything() {
        editor.clear()
        editor.apply()
    }

//    fun countMyReports(): Int {
//
//    }
    /**
     * add
     */
//    editor.putString("key", "value")
//    editor.putInt("another_key", 123)
//    editor.apply()
}