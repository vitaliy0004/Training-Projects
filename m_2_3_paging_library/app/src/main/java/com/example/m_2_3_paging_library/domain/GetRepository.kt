package com.example.m_2_3_paging_library.domain

import com.example.m_2_3_paging_library.data.Repository
import com.example.m_2_3_paging_library.data.characters.RickAndMortyDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetRepository @Inject constructor(private val repository: Repository) {
    suspend fun getRepository(page: Int): RickAndMortyDto {
        return repository.getPerson(page)
    }
}