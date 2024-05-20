package com.example.module_2_13.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()
    var name = ""
    var email = ""
    var age = ""
    var location = ""
    var gender = ""
    var picture = ""
    var control = 0

    fun user() {
        viewModelScope.launch{
            val response = RetrofitInstance().userImegApi.getUser().results[0]
            _state.value = State.Loadin
            delay(300)
            name = "name: ${response.name.first}"
            email = "email: ${response.email}"
            age = "age: ${response.dob.age.toString()}"
            location = "city: ${response.location.city}"
            gender = "gender: ${response.gender}"
            picture =response.picture.medium
            control = 1
            _state.value = State.Success
        }
    }
}



