package com.gsoc2021.ngodonate

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_donate.*
import kotlinx.android.synthetic.main.activity_main.*


class DonateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.objects_list, android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter
    }
    fun submit(view: View) {
        val intent = Intent(applicationContext, FoundNgosActivity::class.java)
        intent.putExtra("object", spinner.selectedItem.toString())
        startActivity(intent)
    }

    fun backBtn(view: View){
        finish()
    }
}