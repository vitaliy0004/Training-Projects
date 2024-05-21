package com.example.modul_2__15.ui.main

sealed class State {
    object Loadin: State()
    object Success: State()
}