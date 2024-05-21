package com.example.m_2_4_jetpack_compose.data.item

import com.example.m_2_4_jetpack_compose.data.Retrofit

class RepositoryItem {
    suspend fun item(page: String): List<Episode> {
        return Retrofit().itemApi.getNewTask(page)
    }
}