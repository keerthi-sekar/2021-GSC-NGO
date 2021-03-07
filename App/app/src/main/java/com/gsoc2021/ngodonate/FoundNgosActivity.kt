package com.gsoc2021.ngodonate

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.gsoc2021.ngodonate.ui.browse.NGO
import com.gsoc2021.ngodonate.ui.browse.NGOAdapter
import kotlinx.android.synthetic.main.activity_found_ngos.*
import kotlinx.android.synthetic.main.activity_main.*


class FoundNgosActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_ngos)
        val intent = intent
        NGO.tag = intent.getStringExtra("object")
        listView = ngo_list_view
        NGO.findNGOs {
            Log.d("browse", "$it")
            val adapter = NGOAdapter(this, it)
            listView.adapter = adapter
        }
    }
    fun backBtn(view: View){
        finish()
    }
}