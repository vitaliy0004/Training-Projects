package com.example.database.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.io.Serializable

@Dao
interface CityDao : Serializable {

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