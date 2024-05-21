package domain

import data.Repository
import data.characters.RickAndMortyDto

class GetRepositoryList {
    suspend fun getRepository(page: Int): RickAndMortyDto {
        return Repository().getPerson(page)
    }
}