package com.example.presentation_patterns.presettation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.presentation_patterns.domain.Repository

class FactoryViewModel(
    private val city: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModule(city) as T
    }
}