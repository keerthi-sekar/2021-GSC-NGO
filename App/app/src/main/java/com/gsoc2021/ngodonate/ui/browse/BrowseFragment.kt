package com.gsoc2021.ngodonate.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gsoc2021.ngodonate.R

class BrowseFragment : Fragment() {

    private lateinit var listView: ListView
    /*var firebasedatabase: FirebaseDatabase? = null
    var NGOList: ArrayList<NGO>?  = null
    var ref: DatabaseReference? = null

    var mRecyclerView: RecycleView? = null*/

    override fun onCreateView(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_browse)

        listView = findViewById<ListView>(R.id.ngo_list_view)

        val NGOList = //get collection from firebase
        val adapter = NGOAdapter(this, NGOList)
        listView.adapter = adapter

        val context = this
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRecipe = ngoList[position]

            // val detailIntent = NGODetailActivity.newIntent(context, selectedRecipe)
            //startActivity(detailIntent)
        }

        return viewOfLayout
    }


}