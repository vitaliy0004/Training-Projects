package com.example.m_1_15_architecture.data

import com.example.m_1_15_architecture.entity.UsefulActivity
import javax.inject.Inject


class UsefulActivitiesRepository @Inject constructor(
    private val taskApi: Retrofit
) {
    suspend fun getUsefulActivity(): UsefulActivity {
        return taskApi.getRetrofitInstance().getNewTask()
    }
}