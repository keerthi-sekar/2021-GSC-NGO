package com.gsoc2021.ngodonate.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gsoc2021.ngodonate.MainActivity
import com.gsoc2021.ngodonate.R
import com.gsoc2021.ngodonate.ui.browse.NGO
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var listView: ListView
    private var firebaseUser: FirebaseUser? = null
    private var db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var points: Int
        var monthlyTarget: Int
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        readData {
            monetaryImpact.text = '$'+it[1]
        }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun readData(myCallback: (ArrayList<String>) -> Unit) {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        db.collection("users").document(firebaseUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                val pointsInfo = ArrayList<String>()
                if (document != null) {
                    val points = document.get("points") as String
                    val monthlyTarget = document.get("monthlyTarget") as String
                    if (points != null)
                        pointsInfo.add(points)
                    if (monthlyTarget != null)
                        pointsInfo.add(monthlyTarget)
                }
                myCallback(pointsInfo)
            }
    }

}