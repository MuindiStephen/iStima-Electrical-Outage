package com.example.istima.services

import android.util.Log
import com.example.istima.utils.Global
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseFirestoreService {

    private val db = Firebase.firestore
    private val TAG = "ABC"

    private var allReports = ArrayList<String>()

    fun postReport(
        time: String, date: String,
        latitude: Double, longitude: Double,
        userEmail: String, userName: String
    ) {
        val post = hashMapOf(
            "userName" to userName,
            "userID" to userEmail,
            "latitude" to latitude,
            "longitude" to longitude,
            "time" to time,
            "date" to date
        )

        db.collection("reports")
            .add(post)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun addUser(userName: String, email: String) {
        val user = hashMapOf(
            "userName" to userName,
            "email" to email
        )

        db.collection("users")
            .add(user)
    }

    fun getAllReports(): List<String> {
        db.collection("reports")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    allReports.add(document.data.toString())
                }
                Log.d("ABC", allReports[0])
                Global.reports = allReports
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        return allReports
    }
}