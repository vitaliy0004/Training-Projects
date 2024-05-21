package com.example.presentation_patterns.di

import com.example.presentation_patterns.domain.Repository
import com.example.presentation_patterns.presettation.FactoryViewModel
//import com.example.presentation_patterns.presettation.FactoryViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideFactory(city:Repository): FactoryViewModel {
        return FactoryViewModel(city)
    }
}
