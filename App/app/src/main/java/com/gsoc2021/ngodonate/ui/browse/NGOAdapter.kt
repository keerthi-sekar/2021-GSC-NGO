package com.gsoc2021.ngodonate.ui.browse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.gsoc2021.ngodonate.R

class NGOAdapter(private val context: Context,
                 private val dataSource: ArrayList<NGO>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    companion object {
        private val LABEL_COLORS = hashMapOf(
            "Books" to R.color.colorPrimary,
            "Clothes" to R.color.colorAccent,
            "Food" to R.color.colorPrimaryDark
        )
    }

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
        val rowView: View
        val holder: ViewHolder
        // 1
        if (convertView == null) {
            // 2
            rowView = inflater.inflate(R.layout.ngo_info, parent, false)

            // 3
            holder = ViewHolder()
            holder.titleTextView = rowView.findViewById(R.id.ngo_title) as TextView
            holder.emailTextView = rowView.findViewById(R.id.ngo_email) as TextView
            // 4
            rowView.tag = holder
        } else {
            // 5
            rowView = convertView
            holder = convertView.tag as ViewHolder
        }

        val titleTextView = holder.titleTextView
        val emailTextView = holder.emailTextView

        val ngo = getItem(position) as NGO

        titleTextView.text = ngo.name
        emailTextView.text = ngo.email

        return rowView
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var emailTextView: TextView
    }

}