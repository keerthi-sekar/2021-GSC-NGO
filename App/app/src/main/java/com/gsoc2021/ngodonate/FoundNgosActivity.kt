package com.gsoc2021.ngodonate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gsoc2021.ngodonate.ui.browse.NGO
import com.gsoc2021.ngodonate.ui.browse.NGOAdapter
import kotlinx.android.synthetic.main.activity_donate.*
import kotlinx.android.synthetic.main.activity_found_ngos.*
import kotlinx.android.synthetic.main.activity_main.*


class FoundNgosActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_ngos)
        val intent = intent
        val objectType = intent.getStringExtra("object")
        NGO.tag = objectType
        listView = ngo_list_view
        NGO.findNGOs {
            Log.d("browse", "$it")
            val adapter = NGOAdapter(this, it)
            listView.adapter = adapter
            // list view item click listener
            listView.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val name: String = it[position].name
                    navigateNgos(name, objectType)
                }
        }
    }
    fun backBtn(view: View){
        finish()
    }
    fun navigateNgos(name: String, objectType: String){
        val intent = Intent(this, NavigationActivity::class.java)
        intent.putExtra("NGOname", name)
        intent.putExtra("objectType", objectType)
        startActivity(intent)
    }

}