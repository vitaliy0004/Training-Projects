package com.example.m_1_17_permissions.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_1_17_permissions.data.Fail
import com.example.m_1_17_permissions.domin.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val STOP_TIMEOUT_MILLIS = 1000L

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val allPhotosOfSights = this.repository.getFailDao()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = emptyList()
        )

    fun savePhoto(photo: String, dataPhoto: String) {
        viewModelScope.launch {
            repository.addFail(
                Fail(
                    fail = photo,
                    data = dataPhoto
                )
            )
        }

    }

    fun delete() {
        viewModelScope.launch {
            allPhotosOfSights.value.let { repository.deleteFail(it) }
        }
    }

}