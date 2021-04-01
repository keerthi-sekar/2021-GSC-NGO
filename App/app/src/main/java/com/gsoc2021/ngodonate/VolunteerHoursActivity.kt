package com.gsoc2021.ngodonate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_volunteer_hours.*

class VolunteerHoursActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer_hours)
        val intent = intent
        val pointRedeemed = intent.getStringExtra("points")
        val volunteerHours = pointRedeemed.toFloat()/100
        volunteerHour.text = volunteerHours.toString()+" hours"
    }
    fun backBtn(view: View){
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}