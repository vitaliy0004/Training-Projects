package com.example.module_2_16_architectur.presentation

sealed class State {
    object Loading : State()
    data class Success(val result: String) : State()
}