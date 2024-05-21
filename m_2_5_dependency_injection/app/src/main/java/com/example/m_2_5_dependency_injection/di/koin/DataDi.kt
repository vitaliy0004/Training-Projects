package com.example.m_2_5_dependency_injection.di.koin

import com.example.m_2_5_dependency_injection.data.FabriceBike
import com.example.m_2_5_dependency_injection.data.FabriceFrame
import com.example.m_2_5_dependency_injection.data.FabriceWheel
import org.koin.dsl.module

val dataModule = module {
    single {
        FabriceWheel()
    }
    single {
        FabriceFrame()
    }
    single {
        FabriceBike(get(), get())
    }
}
