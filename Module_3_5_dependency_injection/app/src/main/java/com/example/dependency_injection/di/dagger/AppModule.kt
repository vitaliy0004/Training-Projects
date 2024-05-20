package com.example.dependency_injection.di.dagger

import dagger.Module
import dagger.Provides
import com.example.dependency_injection.data.FabriceBike
import com.example.dependency_injection.presentation.Factory

@Module
class AppModule {

    @Provides
    fun provideFactory(bike: FabriceBike): Factory {
        return Factory(bike)
    }
}