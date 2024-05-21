package com.example.m_2_3_paging_library.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.m_2_3_paging_library.domain.GetRepository
import javax.inject.Inject

class Factory @Inject constructor(
    private val getRepository: GetRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}