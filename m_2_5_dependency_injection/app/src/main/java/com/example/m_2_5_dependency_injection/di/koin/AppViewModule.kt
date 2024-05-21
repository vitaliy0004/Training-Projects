package com.example.m_2_5_dependency_injection.di.koin

import com.example.m_2_5_dependency_injection.presentation.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single {
        MainViewModel(get())
    }
}