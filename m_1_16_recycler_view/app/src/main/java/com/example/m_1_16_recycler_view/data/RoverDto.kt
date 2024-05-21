package com.example.m_1_16_recycler_view.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.m_1_16_recycler_view.entity.Rover

@JsonClass(generateAdapter = true)
data class RoverDto(
    @Json(name = "cameras") override val cameras: List<com.example.m_1_16_recycler_view.data.CameraXDto>,
    @Json(name = "id") override val id: Int,
    @Json(name = "landing_date") override val landing_date: String,
    @Json(name = "launch_date") override val launch_date: String,
    @Json(name = "max_date") override val max_date: String,
    @Json(name = "max_sol") override val max_sol: Int,
    @Json(name = "name") override val name: String,
    @Json(name = "status") override val status: String,
    @Json(name = "total_photos") override val total_photos: Int
) : Rover