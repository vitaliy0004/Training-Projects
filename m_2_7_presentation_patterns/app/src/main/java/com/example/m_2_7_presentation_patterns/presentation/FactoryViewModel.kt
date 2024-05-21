package com.example.m_2_7_presentation_patterns.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.m_2_7_presentation_patterns.domain.Repository

class FactoryViewModel(
    private val city: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModule(city) as T
    }
}