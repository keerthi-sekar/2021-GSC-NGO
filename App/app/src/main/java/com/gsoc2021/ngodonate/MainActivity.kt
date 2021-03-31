package com.gsoc2021.ngodonate

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_rewards.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {

    private var firebaseUser: FirebaseUser? = null
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseUser = FirebaseAuth.getInstance().currentUser
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val profileImage: CircleImageView = findViewById(R.id.toolbar_profile_image)
        db.collection("users").document(firebaseUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                val photourl = document.get("photourl")

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

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_donate, R.id.navigation_browse, R.id.navigation_rewards
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        profileImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
    fun openObjects(view: View){
        startActivity(
            Intent(
                this,
                DonateActivity::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }
    fun swipeRight(view: View){
        when(SliderCardTitle.text){
            "Popular NGOs" ->{
                SliderCardTitle.text = "Local Rankings"
                item1.text = "Clifton"
                item2.text = "Over-the-Rhine"
                item3.text = "Hyde Park"
                item4.text = "Corryville"
                item5.text = "Mount Airy"
            }
            "Local Rankings" -> {
                SliderCardTitle.text = "Recent Donations"
                item1.text = "Electronics to CityGospel"
                item2.text = "Food to Freestore Foodbank"
                item3.text = "5 Books to Half Price Books"
                item4.text = "Clothes to Matthew 25"
                item5.text = "Clothes to St. Vincent de Paul"
            }
            "Recent Donations" -> {
                SliderCardTitle.text = "Popular NGOs"
                item1.text = "CityGospel"
                item2.text = "Freestore Foodbank"
                item3.text = "Half Price Books"
                item4.text = "Matthew 25"
                item5.text = "St. Vincent de Paul"
            }
        }
    }
    fun swipeLeft(view: View){
        when(SliderCardTitle.text){
            "Local Rankings" ->{
                SliderCardTitle.text = "Popular NGOs"
                item1.text = "CityGospel"
                item2.text = "Freestore Foodbank"
                item3.text = "Half Price Books"
                item4.text = "Matthew 25"
                item5.text = "St. Vincent de Paul"
            }
            "Recent Donations" ->{
                SliderCardTitle.text = "Local Rankings"
                item1.text = "Clifton"
                item2.text = "Over-the-Rhine"
                item3.text = "Hyde Park"
                item4.text = "Corryville"
                item5.text = "Mount Airy"
            }
            "Popular NGOs" ->{
                SliderCardTitle.text = "Recent Donations"
                item1.text = "Electronics to CityGospel"
                item2.text = "Food to Freestore Foodbank"
                item3.text = "5 Books to Half Price Books"
                item4.text = "Clothes to Matthew 25"
                item5.text = "Clothes to St. Vincent de Paul"
            }
        }

    }


    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    fun getHours(view: View)
    {
        Toast.makeText(applicationContext, "Hours Received!", Toast.LENGTH_SHORT).show()
        var currentPoints: Int = 0
        firebaseUser = FirebaseAuth.getInstance().currentUser
        val points: Int = hours.text.toString().toInt()
        readData {
           currentPoints = it.toInt() - points
            db.collection("users").document(firebaseUser!!.uid)
                .update("points", currentPoints.toString())
                .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }
        }
        val intent = Intent(this, VolunteerHoursActivity::class.java)
        intent.putExtra("points", hours.text.toString())
        startActivity(intent)
    }

    fun getCoupon(view: View){
        val intent = Intent(this, CouponActivity::class.java)
        intent.putExtra("storeName", cText.text)
        startActivity(intent)
    }

    fun clickRight(view: View){
        when(cText.text){
            "BlaCk OWNed" ->{
                cText.text = "Smith & Hannon"
                couponBtn.background = resources.getDrawable(R.drawable.smith_and_hannon)
            }
            "Smith & Hannon" -> {
                cText.text = "Toko Baru"
                couponBtn.background = resources.getDrawable(R.drawable.toko_baru)

            }
            "Toko Baru" -> {
                cText.text = "BlaCk OWNed"
                couponBtn.background = resources.getDrawable(R.drawable.black_owned)
            }
        }
    }
    fun clickLeft(view: View){
        when(cText.text){
            "BlaCk OWNed" ->{
                cText.text = "Toko Baru"
                couponBtn.background = resources.getDrawable(R.drawable.toko_baru)
            }
            "Smith & Hannon" -> {
                cText.text = "BlaCk OWNed"
                couponBtn.background = resources.getDrawable(R.drawable.black_owned)

            }
            "Toko Baru" -> {
                cText.text = "Smith & Hannon"
                couponBtn.background = resources.getDrawable(R.drawable.smith_and_hannon)
            }
        }

    }
    fun readData(myCallback: (String) -> Unit) {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        db.collection("users").document(firebaseUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                var points = ""
                if (document != null) {
                    points = document.get("points") as String
                }
                myCallback(points)
            }
    }

}