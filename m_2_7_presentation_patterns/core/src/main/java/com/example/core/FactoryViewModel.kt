package com.example.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.view_model.ViewModule
import com.example.network.domain.Repository
import javax.inject.Inject

class FactoryViewModel @Inject constructor(
    private val city: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModule(city) as T
    }
}