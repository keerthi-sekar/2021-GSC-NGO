package com.gsoc2021.ngodonate.ui.browse

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.gsoc2021.ngodonate.R
import kotlinx.android.synthetic.main.fragment_browse.*

class BrowseFragment : Fragment() {
    
    private lateinit var listView: ListView
    /*var firebasedatabase: FirebaseDatabase? = null
    var NGOList: ArrayList<NGO>?  = null
    var ref: DatabaseReference? = null*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewOfLayout = inflater.inflate(R.layout.fragment_browse, container, false)

        listView = viewOfLayout.findViewById(R.id.ngo_list_view)
        readNGOs()
        return viewOfLayout
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBtn?.setOnClickListener {
            searchNGOs(search_ngos?.text.toString())
        }
    }

    fun searchNGOs(s: String){
        NGO.search = s
        NGO.readData {
            Log.d("browse", "$it")
            val adapter = NGOAdapter(this.requireContext(), it)
            listView.adapter = adapter
        }
    }
    fun readNGOs(){
        NGO.search = "default"
        NGO.readData {
            Log.d("browse", "$it")
            val adapter = NGOAdapter(this.requireContext(), it)
            listView.adapter = adapter
        }
    }

}