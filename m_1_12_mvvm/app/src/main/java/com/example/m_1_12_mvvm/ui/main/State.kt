package com.example.m_1_12_mvvm.ui.main

sealed class State {
    object Loading : State()
    object Success : State()
}
