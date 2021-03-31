package com.gsoc2021.ngodonate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private var firebaseUser: FirebaseUser? = null
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        firebaseUser = FirebaseAuth.getInstance().currentUser
        val profileImage: CircleImageView = findViewById(R.id.profile_image)
        db.collection("users").document(firebaseUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                val photourl = document.get("photourl")
                name.text = document.get("name") as String
                email.text = document.get("email") as String
                points.text = "Current Points: " + document.get("points") as String + " Points"
                monthlyPoints.text = "Monthly Target: " + document.get("monthlyTarget") as String + " Points"
                Glide.with(this)
                    .load(photourl)
                    .placeholder(R.drawable.profile_icon)
                    .into(profileImage)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }
    }
    fun signOut(view: View){
        FirebaseAuth.getInstance().signOut()
        startActivity(
            Intent(
                this,
                WelcomeActivity::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }
    fun backBtn(view: View){
        finish()
    }
}