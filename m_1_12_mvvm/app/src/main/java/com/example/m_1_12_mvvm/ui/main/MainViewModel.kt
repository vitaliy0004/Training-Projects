package com.example.m_1_12_mvvm.ui.main

import android.view.View
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

    fun search() {
        viewModelScope.launch {
            _state.value = State.Loading
            delay(10000)
            _state.value = State.Success
        }
    }
}