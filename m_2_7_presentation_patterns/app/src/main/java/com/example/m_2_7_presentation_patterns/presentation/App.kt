package com.example.m_2_7_presentation_patterns.presentation

import android.app.Application
import androidx.room.Room
import com.example.m_2_7_presentation_patterns.di.AppComponent
import com.example.m_2_7_presentation_patterns.di.DaggerAppComponent
import com.example.m_2_7_presentation_patterns.data.databas.AppDatabase

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

