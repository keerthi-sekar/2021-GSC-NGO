package com.gsoc2021.ngodonate.ui.monetary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonetaryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is monetary Fragment"
    }
    val text: LiveData<String> = _text
}