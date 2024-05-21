package com.example.m_2_7_presentation_patterns.data.retrofit

import com.example.m_2_7_presentation_patterns.data.retrofit.dto.TimerDto
import javax.inject.Inject

class GetRepository @Inject constructor(private val retrofit: Retrofit) {
    suspend fun getCityWeather(city: String): TimerDto {
        return retrofit.takeApi.taskCity(city)
    }
}