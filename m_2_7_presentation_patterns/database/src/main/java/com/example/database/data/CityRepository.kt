package com.example.database.data

import javax.inject.Inject

class CityRepository @Inject constructor(private val cityDao: CityDao) {
    fun getCities() = cityDao
}