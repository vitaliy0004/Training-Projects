package com.example.network.domain

import com.example.network.data.dto.TimerDto
import com.example.network.data.GetRepository
import javax.inject.Inject


class Repository @Inject constructor(private val getRepository: GetRepository) {
    suspend fun cityWeather(city: String): TimerDto {
        return getRepository.getCityWeather(city)
    }
}