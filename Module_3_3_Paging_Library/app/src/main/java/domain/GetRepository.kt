package domain

import data.Repository
import data.characters.RickAndMortyDto
import kotlinx.coroutines.delay

class GetRepository() {
    suspend fun getRepository(page:Int):RickAndMortyDto{
        return Repository().getPerson(page)
    }
}