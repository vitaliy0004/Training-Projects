package com.example.m_2_7_presentation_patterns.data.databas

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityTemperature::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}