package com.example.dependency_injection.di.dagger

import dagger.Component
import com.example.dependency_injection.presentation.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, Dagger::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}