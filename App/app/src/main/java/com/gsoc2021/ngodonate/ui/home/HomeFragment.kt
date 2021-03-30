package com.gsoc2021.ngodonate.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import com.faskn.lib.buildChart
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gsoc2021.ngodonate.MainActivity
import com.gsoc2021.ngodonate.R
import com.gsoc2021.ngodonate.ui.browse.NGO
import kotlinx.android.synthetic.main.fragment_home.*
import org.eazegraph.lib.models.PieModel
import kotlin.random.Random

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
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        readData {
            var progressPercentage: Float = it[0].toFloat()/it[1].toFloat()*360
            val monetaryValue = it[0].toInt()/50*27
            Log.d("debug1", "$progressPercentage")
            val pieChartDSL = buildChart {
                slices { arrayListOf(
                    Slice(
                        progressPercentage,
                        R.color.colorPrimary,
                        "Google"
                    ),
                    Slice(
                        360-progressPercentage,
                        R.color.colorBackground,
                        "Facebook"
                    )
                ) }
                sliceWidth { 80f }
                sliceStartPoint { 270f }
                clickListener { angle, index ->
                    // ...
                }
            }
            chart.setPieChart(pieChartDSL)
            monetaryImpact.text = '$'+monetaryValue.toString()
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
                    pointsInfo.add(points)
                    pointsInfo.add(monthlyTarget)
                }
                myCallback(pointsInfo)
            }
    }
}