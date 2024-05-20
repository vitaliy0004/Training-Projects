package nasaAPI

import data.PhotoSputnikDto
import javax.inject.Inject

class Repository @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getPhotoSputnik(page: Int): PhotoSputnikDto {
        return retrofit.taskApi.getNewTask(page= page, sol = 1000)
    }
}