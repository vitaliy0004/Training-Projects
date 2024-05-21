package com.example.m_2_7_presentation_patterns.domain

import com.example.m_2_7_presentation_patterns.data.retrofit.dto.TimerDto
import com.example.m_2_7_presentation_patterns.data.retrofit.GetRepository
import javax.inject.Inject

class Repository @Inject constructor(private val getRepository: GetRepository) {
    suspend fun cityWeather(city: String): TimerDto {
        return getRepository.getCityWeather(city)
    }
}