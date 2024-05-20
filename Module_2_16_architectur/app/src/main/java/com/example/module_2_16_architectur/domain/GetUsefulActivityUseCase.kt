package com.example.module_2_16_architectur.domain

import com.example.module_2_16_architectur.data.UsefulActivitiesRepository
import com.example.module_2_16_architectur.entity.UsefulActivity
import javax.inject.Inject

class GetUsefulActivityUseCase @Inject constructor(
   private val usefulActivitiesRepository: UsefulActivitiesRepository
) {
    suspend fun execute(): UsefulActivity{
     return usefulActivitiesRepository.getUsefulActivity()
    }
}