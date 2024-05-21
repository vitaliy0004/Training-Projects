package com.example.m_1_15_architecture.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

private const val BASE_URL = "https://www.boredapi.com/api/"

class Retrofit @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val taskApi = retrofit.create(TaskApi::class.java)
    fun getRetrofitInstance(): TaskApi = taskApi

}