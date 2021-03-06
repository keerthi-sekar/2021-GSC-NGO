package com.gsoc2021.ngodonate.ui.browse

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class NGO(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val location: String,
    val volunteer: String,
    val website: String) {


    companion object {

        private var db = Firebase.firestore
        private lateinit var auth: FirebaseAuth
        val docRef = db.collection("NGOs")

        val documents: MutableList<DocumentSnapshot>? = null

        fun readData(myCallback: (ArrayList<NGO>) -> Unit) {
            docRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val ngoList = ArrayList<NGO>()
                    for (document in task.result) {
                        val email = document.get("email") as String
                        val currentNgo = NGO(
                            document.get("name") as String,
                            document.get("email") as String,
                            document.get("phone_number") as String,
                            document.get("hq_location") as String,
                            document.get("volunteer") as String,
                            document.get("website") as String
                        )
                        Log.d("EMAIL", email)
                        ngoList.add(currentNgo)
                    }
                    myCallback(ngoList)
                }
            }
        }
    }
}