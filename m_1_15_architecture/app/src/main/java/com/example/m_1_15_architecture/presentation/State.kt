package com.example.m_1_15_architecture.presentation

sealed class State {
    object Loading : State()
    data class Success(val result: String) : State()
}