package com.gsoc2021.ngodonate.ui.browse

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import org.json.JSONException
import org.json.JSONObject
import java.lang.NullPointerException

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
        val ngoList = ArrayList<NGO>()
        val documents: MutableList<DocumentSnapshot>? = null

        suspend fun getNGOsFromFile(): ArrayList<NGO> {

            val documents = getListOfNgos()

            for(document in documents){
                val email = document.get("email") as String
                val current_ngo = NGO(
                    document.get("name") as String,
                    document.get("email") as String,
                    document.get("phone_number") as String,
                    document.get("hq_location") as String,
                    document.get("volunteer") as String,
                    document.get("website") as String
                )
                Log.d("EMAIL",  "$email" )
                ngoList.add(current_ngo)
            }


            /*docRef.get()
                .addOnCompleteListener { task ->
                    if(task.isSuccessful)
                    {
                        for(document in task.result){
                            val email = document.get("email") as String
                            val current_ngo = NGO(
                                document.get("name") as String,
                                document.get("email") as String,
                                document.get("phone_number") as String,
                                document.get("hq_location") as String,
                                document.get("volunteer") as String,
                                document.get("website") as String
                            )
                            Log.d("EMAIL",  "$email" )
                            ngoList.add(current_ngo)
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("FAILURE", "get failed with ", exception)
                }
                   */

            for(ngo in ngoList)
            {
                Log.d("TESTinfinity","${ngo.name}")
                Log.d("TESTinfinity", "${ngoList.size}")
            }
            return ngoList
        }

        private suspend fun getListOfNgos(): MutableList<DocumentSnapshot> {
            val snapshot = docRef.get().await()

            return snapshot.documents
        }
    }
}