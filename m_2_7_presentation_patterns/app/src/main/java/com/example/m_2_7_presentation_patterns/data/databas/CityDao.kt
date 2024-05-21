package com.example.m_2_7_presentation_patterns.data.databas

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * FROM cityTemperature")
    fun getCity(): Flow<List<CityTemperature>>

    @Query("SELECT * FROM cityTemperature WHERE city LIKE :city")
    suspend fun getHistory(city: String): List<CityTemperature>

    @Insert
    suspend fun addCityTemperature(cityTemperature: CityTemperature)

    @Delete
    suspend fun delete(cityTemperature: CityTemperature)

    @Update
    suspend fun update(city: CityTemperature)
}