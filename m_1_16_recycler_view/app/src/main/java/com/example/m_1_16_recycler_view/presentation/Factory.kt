package com.example.m_1_16_recycler_view.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.m_1_16_recycler_view.domain.GetRepository
import com.example.m_1_16_recycler_view.nasaAPI.Repository
import javax.inject.Inject


class Factory @Inject constructor(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalStateException("Unknown class name")
    }
}