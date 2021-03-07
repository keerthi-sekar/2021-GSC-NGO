package com.gsoc2021.ngodonate.ui.donate

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.gsoc2021.ngodonate.R
import com.gsoc2021.ngodonate.WelcomeActivity

class DonateFragment : Fragment() {

    private lateinit var donateViewModel: DonateViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        donateViewModel =
            ViewModelProviders.of(this).get(DonateViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_donate, container, false)

        return root
    }

}