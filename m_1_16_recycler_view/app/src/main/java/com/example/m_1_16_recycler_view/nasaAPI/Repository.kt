package com.example.m_1_16_recycler_view.nasaAPI

import com.example.m_1_16_recycler_view.data.PhotoSputnikDto
import com.example.m_1_16_recycler_view.domain.GetRepository
import javax.inject.Inject

class Repository @Inject constructor(
    private val getRetrofit: GetRepository
) {
    suspend fun getPhotoSputnik(page: Int): PhotoSputnikDto {
        return getRetrofit.getSputnik(page)
    }
}