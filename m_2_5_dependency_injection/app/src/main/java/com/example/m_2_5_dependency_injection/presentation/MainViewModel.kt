package com.example.m_2_5_dependency_injection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_2_5_dependency_injection.data.FabriceBike
import com.example.m_2_5_dependency_injection.entity.Bike
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val bike: FabriceBike
) : ViewModel() {
    private val _bike = Channel<Bike>()
    val bikes = _bike.receiveAsFlow()
    private lateinit var currentBike: Bike
    fun createBike(logo: String, color: Int, number: Int) {
        currentBike = bike.build(logo, color, number)
        viewModelScope.launch {
            _bike.send(currentBike)
        }
    }
}