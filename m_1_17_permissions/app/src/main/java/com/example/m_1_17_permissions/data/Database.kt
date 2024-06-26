package com.example.m_1_17_permissions.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Fail::class], version = 1, exportSchema = true)
abstract class Database : RoomDatabase() {
    abstract fun failDao(): FailDao
}