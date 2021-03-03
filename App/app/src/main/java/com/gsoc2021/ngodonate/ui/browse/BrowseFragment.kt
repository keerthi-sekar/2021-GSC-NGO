package com.gsoc2021.ngodonate.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.gsoc2021.ngodonate.R

class BrowseFragment : Fragment() {

    private lateinit var viewOfLayout: View
    private lateinit var listView: ListView
    /*var firebasedatabase: FirebaseDatabase? = null
    var NGOList: ArrayList<NGO>?  = null
    var ref: DatabaseReference? = null*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val viewOfLayout = inflater.inflate(R.layout.fragment_browse, container, false)

        listView = viewOfLayout.findViewById(R.id.ngo_list_view)

        val ngoList = NGO.getNGOsFromFile("ngo.json", this)

        val adapter = NGOAdapter(this, ngoList)
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