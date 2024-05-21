package com.example.dependency_injection.di.koin

import com.example.dependency_injection.presentation.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single {
        MainViewModel(get())
    }
}