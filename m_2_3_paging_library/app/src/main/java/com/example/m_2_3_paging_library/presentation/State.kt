package com.example.m_2_3_paging_library.presentation

sealed class State {
    object Loading : State()
    object Success : State()
    object Error : State()
}
