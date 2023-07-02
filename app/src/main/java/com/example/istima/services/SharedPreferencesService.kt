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

    /**
     * add
     */
//    editor.putString("key", "value")
//    editor.putInt("another_key", 123)
//    editor.apply()
}