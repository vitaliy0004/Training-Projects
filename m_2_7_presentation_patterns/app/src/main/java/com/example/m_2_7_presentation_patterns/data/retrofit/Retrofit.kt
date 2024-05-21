package com.example.m_2_7_presentation_patterns.data.retrofit

import com.example.m_2_7_presentation_patterns.data.retrofit.dto.TimerDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class Retrofit @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BAS_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val takeApi: UseCity = retrofit.create(UseCity::class.java)
}

const val API_KEY = "jOLeA77iILdDVprhODuUdWAlLBVffnzU"
const val BAS_URL = "https://api.tomorrow.io/v4/weather/"

interface UseCity {
    @GET("forecast?apikey=$API_KEY")
    suspend fun taskCity(@Query("location") name: String): TimerDto
}