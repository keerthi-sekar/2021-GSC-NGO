package com.gsoc2021.ngodonate.ui.donate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DonateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is donate Fragment"
    }
    val text: LiveData<String> = _text
}