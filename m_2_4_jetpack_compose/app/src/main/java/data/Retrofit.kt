package data

import data.characters.RickAndMortyDto
import data.item.Episode
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class Retrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val taskApi: TaskApi = retrofit.create(TaskApi::class.java)
    val itemApi: ItemApi = retrofit.create(ItemApi::class.java)
}

val BASE_URL = "https://rickandmortyapi.com/api/"

interface TaskApi {
    @GET("character/")
    suspend fun getNewTask(@Query("page") page: Int): RickAndMortyDto
}

interface ItemApi {
    @GET("episode/{episode_id}")
    suspend fun getNewTask(@Path("episode_id") episodeId: String): List<Episode>
}