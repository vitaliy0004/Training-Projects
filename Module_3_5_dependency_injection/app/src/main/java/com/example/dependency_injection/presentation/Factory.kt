package com.example.dependency_injection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dependency_injection.data.FabriceBike

class Factory(
    private val bike: FabriceBike
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(bike) as T
    }
}