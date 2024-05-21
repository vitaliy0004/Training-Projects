package com.example.m_2_4_jetpack_compose.data

import com.example.m_2_4_jetpack_compose.data.characters.RickAndMortyDto
import com.example.m_2_4_jetpack_compose.data.item.Episode
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class Retrofit @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val taskApi: TaskApi = retrofit.create(TaskApi::class.java)
    val itemApi: ItemApi = retrofit.create(ItemApi::class.java)
}

const val BASE_URL = "https://rickandmortyapi.com/api/"

interface TaskApi {
    @GET("character/")
    suspend fun getNewTask(@Query("page") page: Int): RickAndMortyDto
}

interface ItemApi {
    @GET("episode/{episode_id}")
    suspend fun getNewTask(@Path("episode_id") episodeId: String): List<Episode>
}