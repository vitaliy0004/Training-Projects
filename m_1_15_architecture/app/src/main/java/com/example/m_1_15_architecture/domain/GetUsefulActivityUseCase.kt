package com.example.m_1_15_architecture.domain

import com.example.m_1_15_architecture.data.UsefulActivitiesRepository
import com.example.m_1_15_architecture.entity.UsefulActivity
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
    private val usefulActivitiesRepository: UsefulActivitiesRepository
) {
    suspend fun execute(): UsefulActivity {
        return usefulActivitiesRepository.getUsefulActivity()
    }
}