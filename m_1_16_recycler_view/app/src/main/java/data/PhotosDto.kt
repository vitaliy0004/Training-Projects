package data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import entity.Photos

@JsonClass(generateAdapter = true)
data class PhotosDto(
    @Json(name = "camera") override val camera: CameraDto,
    @Json(name = "earth_date")  override val earthDate: String,
    @Json(name = "id")  override val id: Int,
    @Json(name = "img_src") override val img_src: String,
    @Json(name = "rover") override val rover: RoverDto,
    @Json(name = "sol")  override val sol: Int
):Photos