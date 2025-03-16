package com.example.database.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [CityTemperature::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}