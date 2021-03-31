package com.gsoc2021.ngodonate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_coupon.*

class CouponActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon)
        val intent = intent
        storeName.text = intent.getStringExtra("storeName")
    }
    fun backBtn(view: View){
        finish()
    }
}