package com.gsoc2021.ngodonate.ui.browse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gsoc2021.ngodonate.R
import kotlinx.coroutines.tasks.await

class BrowseFragment : Fragment() {
    
    private lateinit var listView: ListView
    /*var firebasedatabase: FirebaseDatabase? = null
    var NGOList: ArrayList<NGO>?  = null
    var ref: DatabaseReference? = null*/
    val ngoList = ArrayList<NGO>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewOfLayout = inflater.inflate(R.layout.fragment_browse, container, false)

        listView = viewOfLayout.findViewById(R.id.ngo_list_view)

        val ngoList = NGO.getNGOsFromFile()
        Log.d("browse","${ngoList.size}")

        val adapter = NGOAdapter(this.requireContext(), ngoList)
        listView.adapter = adapter

        return viewOfLayout
    }

    companion object {

        private var db = Firebase.firestore
        private lateinit var auth: FirebaseAuth
        val docRef = db.collection("NGOs")
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