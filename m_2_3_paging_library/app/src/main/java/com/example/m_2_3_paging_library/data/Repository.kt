package com.example.m_2_3_paging_library.data

import com.example.m_2_3_paging_library.data.characters.RickAndMortyDto
import javax.inject.Inject


class Repository @Inject constructor(private val retrofit: Retrofit) {
    suspend fun getPerson(page: Int): RickAndMortyDto {
        return retrofit.taskApi.getNewTask(page)
    }
}