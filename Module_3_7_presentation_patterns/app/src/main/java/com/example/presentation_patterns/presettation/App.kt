package com.example.presentation_patterns.presettation

import android.app.Application
import androidx.room.Room
import com.example.presentation_patterns.di.AppComponent
import com.example.presentation_patterns.di.DaggerAppComponent
import com.example.presentation_patterns.data.databas.AppDatabase

class App : Application() {
    lateinit var appComponent: AppComponent
    lateinit var db: AppDatabase
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "db"
        ).build()
    }
}

