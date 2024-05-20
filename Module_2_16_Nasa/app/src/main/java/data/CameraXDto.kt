package data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import entity.CameraX

@JsonClass(generateAdapter = true)
data class CameraXDto(
    @Json(name = "full_name")override val full_name: String,
    @Json(name = "name")override val name: String
): CameraX