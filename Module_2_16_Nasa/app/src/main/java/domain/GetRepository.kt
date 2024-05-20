package domain

import data.PhotoSputnikDto
import nasaAPI.Repository
import javax.inject.Inject

class GetRepository @Inject constructor(
    private val repository: Repository
) {
    suspend fun getSputnik():PhotoSputnikDto{
        return repository.getPhotoSputnik(1)
    }
}