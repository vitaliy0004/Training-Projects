package com.example.m_2_5_dependency_injection.di.dagger

import dagger.Module
import dagger.Provides
import com.example.m_2_5_dependency_injection.data.FabriceBike
import com.example.m_2_5_dependency_injection.presentation.Factory

@Module
class AppModule {

    @Provides
    fun provideFactory(bike: FabriceBike): Factory {
        return Factory(bike)
    }
}