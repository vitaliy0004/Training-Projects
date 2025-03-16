package com.example.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cityTemperature")
data class CityTemperature(
    @PrimaryKey
    @ColumnInfo(name = "city_and_data")
    val cityAndData: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "data")
    val data: String,
    @ColumnInfo(name = "temperature")
    val temperature: String
)
