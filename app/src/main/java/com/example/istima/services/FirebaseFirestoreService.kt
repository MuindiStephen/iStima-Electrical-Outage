package com.example.istima.services

import android.content.Context
import android.util.Log
import com.example.istima.utils.Global
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Collections

class FirebaseFirestoreService(
    private var context: Context
) {

    private val db = Firebase.firestore
    private val TAG = "ABC"

    private lateinit var theUserName: String

    private var allReports = ArrayList<String>()

    val sharedPreferences = context.getSharedPreferences(Global.sharedPreferencesName, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val mapService = MapService()


    fun postReport(
        time: String, date: String,
        latitude: Double, longitude: Double,
        userId: String, userName: String,
        description: String
    ) {
        Log.d("ABC", "username: $userName")
        val post = hashMapOf(
            "userID" to userId,
            "latitude" to latitude,
            "longitude" to longitude,
            "time" to time,
            "date" to date,
            "userName" to userName,
            "description" to description
        )

        val timestamp = Timestamp.now()
        val documentID = timestamp.toDate().time.toString() + " · " + userId
        val documentRef = db.collection("reports").document(documentID)

        documentRef.set(post)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: $documentID")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error adding document")
            }
    }

    fun addUser(userName: String, email: String, userId: String) {
        val user = hashMapOf(
            "userName" to userName,
            "email" to email,
            "uid" to userId
        )

        db.collection("users")
            .add(user)
    }

    fun getUserName(userId: String) {
        var userName: String
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data["uid"] == userId) {
                        userName = document.data["userName"].toString()
                        editor.putString(Global.sharedPreferencesUserName, userName)
                        editor.apply()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting Username", exception)
            }
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
                }
                Log.d("ABC", allReports.toString())
                allReports.reverse()
                mapService.extractCoordinatesFromArrayList(allReports)
                Global.reports = allReports
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        return allReports
    }
}