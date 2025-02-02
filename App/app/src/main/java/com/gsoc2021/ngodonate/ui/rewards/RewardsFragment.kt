package com.gsoc2021.ngodonate.ui.rewards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gsoc2021.ngodonate.R
import kotlinx.android.synthetic.main.fragment_rewards.*


class RewardsFragment : Fragment() {

    private lateinit var rewardsViewModel: RewardsViewModel
    var currentPoints = 20
    var monthlygoal = 300
    private var firebaseUser: FirebaseUser? = null
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rewardsViewModel =
            ViewModelProviders.of(this).get(RewardsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_rewards, container, false)

        readData {
            monthlyTarget.text = it[0]+" points"
        }

        //Card4!!.layoutParams.width  = progressPercentage * Card3.width
        return root
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