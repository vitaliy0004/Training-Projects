package nasaAPI
import data.PhotoSputnikDto
import nasaAPI.TaskApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject


interface TaskApi {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getNewTask(
        @Query("page") page: Int,
        @Query("sol") sol: Int,
        @Query("api_key") api_key: String = API_KEY
    ): PhotoSputnikDto
companion object {

    const val API_KEY = "6MRxrXqtAI0CrnhQQAo9SzQvjerJRHG4rIeQPepX"
    const val BASE_URL = "https://api.nasa.gov"
}

}



class Retrofit @Inject constructor(){
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
     val taskApi: TaskApi = retrofit.create(TaskApi::class.java)
}
