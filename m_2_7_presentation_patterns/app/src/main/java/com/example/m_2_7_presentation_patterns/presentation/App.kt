package com.example.m_2_7_presentation_patterns.presentation

import android.app.Application
import com.example.database.data.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var database: AppDatabase
}

