package com.example.m_1_16_recycler_view.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.m_1_16_recycler_view.entity.Photos

@JsonClass(generateAdapter = true)
data class PhotosDto(
    @Json(name = "camera") override val camera: com.example.m_1_16_recycler_view.data.CameraDto,
    @Json(name = "earth_date") override val earthDate: String,
    @Json(name = "id") override val id: Int,
    @Json(name = "img_src") override val img_src: String,
    @Json(name = "rover") override val rover: com.example.m_1_16_recycler_view.data.RoverDto,
    @Json(name = "sol") override val sol: Int
) : Photos