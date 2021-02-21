package com.gsoc2021.ngodonate.ui.browse

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gsoc2021.ngodonate.R

class NGOAdapter(private val context: Context,
                 private val dataSource: ArrayList<NGO>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }


    override fun getItem(position: Int): Any {
        return dataSource[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.ngo_info, parent, false)

        val titleTextView = rowView.findViewById(R.id.ngo_title) as TextView
        val emailTextView = rowView.findViewById(R.id.ngo_email) as TextView

        val ngo = getItem(position) as NGO

        titleTextView.text = ngo.name
        emailTextView.text = ngo.email

        return rowView
    }

}