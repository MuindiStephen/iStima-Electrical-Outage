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
        userId: String, userName: String
    ) {
        val post = hashMapOf(
            "userName" to userName,
            "userID" to userId,
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

    fun getUserName(userId: String): String {
        var userName = ""
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data["userID"] == userId) {
                        userName = document.data["userName"].toString()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        return userName
    }

    fun getAllReports(): List<String> {
        db.collection("reports")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
//                    Log.d("ABC", document.data)
                    var report = "{"
                    for (i in 0 until document.data.size) {
                        var keys = document.data.keys.toList()
                        var values = document.data.values.toList()
                        report += "\"${keys[i]}\": \"${values[i]}\","
                    }
                    report = report.dropLast(1)
                    report += "}"
                    allReports.add(report)
//                    parseToJson(document.data.toString()).let { allReports.add(it) }
                }
                Log.d("ABC", allReports.toString())
                Global.reports = allReports
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        return allReports
    }
}