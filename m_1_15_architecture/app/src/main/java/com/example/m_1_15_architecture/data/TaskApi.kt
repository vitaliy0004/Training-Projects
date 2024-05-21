package com.example.m_1_15_architecture.data

import retrofit2.http.GET

interface TaskApi {
    @GET("activity/")
    suspend fun getNewTask(): UsefulActivityDto
}