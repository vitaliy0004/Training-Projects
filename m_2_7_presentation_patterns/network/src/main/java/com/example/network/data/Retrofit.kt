package com.example.network.data

import retrofit2.Retrofit
import com.example.network.data.dto.TimerDto
import dagger.Module
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject


class Retrofit  @Inject constructor(){
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