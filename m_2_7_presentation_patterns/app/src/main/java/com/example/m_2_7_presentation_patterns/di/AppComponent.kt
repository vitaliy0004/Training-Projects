package com.example.m_2_7_presentation_patterns.di

import com.example.m_2_7_presentation_patterns.presentation.fragment.FragmentWeather
import com.example.m_2_7_presentation_patterns.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, Dagger::class])
interface AppComponent {
    fun inject(fragment: FragmentWeather)
    fun inject(mainActivity: MainActivity)
}