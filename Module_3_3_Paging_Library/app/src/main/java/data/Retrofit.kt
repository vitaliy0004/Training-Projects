package data
import data.characters.RickAndMortyDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Retrofit  {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val taskApi: TaskApi = retrofit.create(TaskApi::class.java)
}
val BASE_URL = "https://rickandmortyapi.com/"
interface TaskApi{
    @GET("api/character")
    suspend fun getNewTask(@Query("page")page: Int): RickAndMortyDto
}