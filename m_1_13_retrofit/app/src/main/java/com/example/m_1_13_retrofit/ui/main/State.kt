package com.example.m_1_13_retrofit.ui.main

sealed class State() {
    object Loading : State()
    object Success : State()
}
