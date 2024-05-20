package data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import entity.PhotoSputnik

@JsonClass(generateAdapter = true)
data class PhotoSputnikDto(
    @Json(name = "photos")override val photos: List<PhotosDto>
): PhotoSputnik
