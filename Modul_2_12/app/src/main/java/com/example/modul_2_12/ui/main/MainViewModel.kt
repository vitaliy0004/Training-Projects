package com.example.modul_2_12.ui.main

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    fun enabled(int: Int, view: View) {
        view.isEnabled = int >= 3
    }
    fun search(viewButton: View,viewProgress: View,viewText: TextView,viewInput: View){
        viewModelScope.launch {
            _state.value =State.Loadin
             delay(10000)
            _state.value =State.Success
        }
    }
}