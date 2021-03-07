package com.gsoc2021.ngodonate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_donate.*

class DonateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        val adapter = ArrayAdapter.createFromResource(this,
            R.array.city_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter
    }

    fun getValues(view: View) {
        Toast.makeText(this, "Spinner 1 " + spinner.selectedItem.toString(), Toast.LENGTH_LONG).show()
    }
}