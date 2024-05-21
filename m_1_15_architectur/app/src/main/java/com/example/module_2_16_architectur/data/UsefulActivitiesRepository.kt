package com.example.module_2_16_architectur.data

import com.example.module_2_16_architectur.entity.UsefulActivity
import javax.inject.Inject


class UsefulActivitiesRepository @Inject constructor(
    private val taskApi: Retrofit
        ){
    suspend fun getUsefulActivity(): UsefulActivity{
        return taskApi.getRetrofitInstance().getNewTask()
    }
}