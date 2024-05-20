package com.example.dependency_injection

import android.app.Application
import com.example.dependency_injection.di.dagger.AppComponent
import com.example.dependency_injection.di.dagger.DaggerAppComponent
import com.example.dependency_injection.di.koin.appModule
import com.example.dependency_injection.di.koin.dataModule
import org.koin.core.context.startKoin


class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

        startKoin {
            modules(listOf(appModule, dataModule))
        }
    }
}