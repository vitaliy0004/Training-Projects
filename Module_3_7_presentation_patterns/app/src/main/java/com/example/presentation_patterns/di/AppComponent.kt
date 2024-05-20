package com.example.presentation_patterns.di

import com.example.presentation_patterns.presettation.fragment.FragmentWeather
import com.example.presentation_patterns.presettation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,Dagger::class])
interface AppComponent {
    fun inject(fragment: FragmentWeather)
    fun inject(mainActivity: MainActivity)
}