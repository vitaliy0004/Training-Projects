package com.example.m_1_15_architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_1_15_architecture.domain.GetUsefulActivityUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val useCase: GetUsefulActivityUseCase
) : ViewModel() {
    val _state = MutableStateFlow<State>(State.Success(""))
    val state = _state.asStateFlow()

    fun reloadUsefulActivity() {
        _state.value = State.Loading
        viewModelScope.launch {
            delay(1000)
            val task = useCase.execute()
            _state.value = State.Success(task.activity)
        }


    }
}