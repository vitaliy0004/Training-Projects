package com.example.m_2_7_presentation_patterns.di

import com.example.m_2_7_presentation_patterns.domain.Repository
import com.example.m_2_7_presentation_patterns.presentation.FactoryViewModel
//import com.example.presentation_patterns.presettation.FactoryViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideFactory(city: Repository): FactoryViewModel {
        return FactoryViewModel(city)
    }
}
