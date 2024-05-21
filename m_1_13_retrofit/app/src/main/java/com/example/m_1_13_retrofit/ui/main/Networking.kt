package com.example.m_1_13_retrofit.ui.main

import com.example.m_1_13_retrofit.Json.RandomUser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me/"


class RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val userImageApi: SearchImageApi = retrofit.create(SearchImageApi::class.java)
}


interface SearchImageApi {
    @GET("api")
    suspend fun getUser(): RandomUser
}
