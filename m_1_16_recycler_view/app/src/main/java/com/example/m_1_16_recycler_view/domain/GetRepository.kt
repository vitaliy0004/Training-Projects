package com.example.m_1_16_recycler_view.domain

import com.example.m_1_16_recycler_view.data.PhotoSputnikDto
import com.example.m_1_16_recycler_view.nasaAPI.Repository
import com.example.m_1_16_recycler_view.nasaAPI.Retrofit
import javax.inject.Inject

class GetRepository @Inject constructor(
    private val repository: Retrofit
) {
    suspend fun getSputnik(page: Int): PhotoSputnikDto {
        return repository.taskApi.getNewTask(page = page, sol = 1000)
    }
}