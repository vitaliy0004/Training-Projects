package com.example.m_1_17_permissions.di

import android.content.Context
import androidx.room.Room
import com.example.m_1_17_permissions.data.Database
import com.example.m_1_17_permissions.data.FailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseDi {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "bd"
        ).build()
    }

    @Provides
    fun provideDao(appDataBase: Database): FailDao {
        return appDataBase.failDao()
    }

}