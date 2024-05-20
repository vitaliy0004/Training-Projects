package com.example.module_2_16_architectur.data

import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

private const val BASE_URL  ="https://www.boredapi.com/api/"
class Retrofit @Inject constructor(){
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val taskApi = retrofit.create(TaskApi::class.java)
    fun getRetrofitInstance():TaskApi = taskApi

}