package com.example.m_1_14_room.ui.main

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Words::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao
}