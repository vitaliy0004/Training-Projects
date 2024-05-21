package com.example.module_2_16_architectur.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_2_16_architectur.domain.GetUsefulActivityUseCase
import com.example.module_2_16_architectur.entity.UsefulActivity
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun reloadUsefulActivity(){
       _state.value = State.Loading
        viewModelScope.launch { delay(1000)
            val task = useCase.execute()
            _state.value = State.Success(task.activity)
        }


   }
}