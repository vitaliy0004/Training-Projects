package com.example.m_2_4_jetpack_compose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.m_2_4_jetpack_compose.domain.GetRepositoryList
import javax.inject.Inject

class Factory @Inject constructor(
    private val getRepository: GetRepositoryList
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}