package com.gsoc2021.ngodonate.ui.monetary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gsoc2021.ngodonate.R

class MonetaryFragment : Fragment() {

    private lateinit var monetaryViewModel: MonetaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        monetaryViewModel =
            ViewModelProviders.of(this).get(MonetaryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_monetary, container, false)
        val textView: TextView = root.findViewById(R.id.text_monetary)
        monetaryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}