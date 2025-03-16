package com.example.network.data

import com.example.network.data.dto.TimerDto
import com.example.network.domain.Repository
import javax.inject.Inject

class GetRepository @Inject constructor(private val retrofit: Retrofit) {
    suspend fun getCityWeather(city: String): TimerDto {
        return retrofit.takeApi.taskCity(city)
    }
}