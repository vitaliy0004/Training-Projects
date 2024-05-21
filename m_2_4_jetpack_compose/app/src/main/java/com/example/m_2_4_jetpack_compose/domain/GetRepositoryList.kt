package com.example.m_2_4_jetpack_compose.domain

import com.example.m_2_4_jetpack_compose.data.Repository
import com.example.m_2_4_jetpack_compose.data.Retrofit
import com.example.m_2_4_jetpack_compose.data.characters.RickAndMortyDto
import javax.inject.Inject

class GetRepositoryList @Inject constructor(private val repository: Repository) {
    suspend fun getRepository(page: Int): RickAndMortyDto {
        return repository.getPerson(page)
    }
}