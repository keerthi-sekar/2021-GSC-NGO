package com.gsoc2021.ngodonate

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {

    private var firebaseUser: FirebaseUser? = null
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseUser = FirebaseAuth.getInstance().currentUser
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        var name: String
        var firstName: String
        val profileImage: CircleImageView = findViewById(R.id.toolbar_profile_image)
        db.collection("users").document(firebaseUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                name = document.get("name") as String
                val words = name.split(" ")
                firstName = words[0]
                val photourl = document.get("photourl")
                supportActionBar?.title = "Hi, $firstName"
                Glide.with(this)
                    .load(photourl)
                    .placeholder(R.drawable.profile_icon)
                    .into(profileImage)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }
        val navController = findNavController(R.id.nav_host_fragment)

        setSupportActionBar(toolbar)

        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        profileImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}