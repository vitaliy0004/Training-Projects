package data

import data.characters.RickAndMortyDto



class Repository {
     suspend fun getPerson(page:Int): RickAndMortyDto {
        return Retrofit().taskApi.getNewTask(page)
    }
}