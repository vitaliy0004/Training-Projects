package com.example.module_2_16_architectur.data

import retrofit2.http.GET

interface TaskApi {
    @GET("activity/")
      suspend fun getNewTask():  UsefulActivityDto

}