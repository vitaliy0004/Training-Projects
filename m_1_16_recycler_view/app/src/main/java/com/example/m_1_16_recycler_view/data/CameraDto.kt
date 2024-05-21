package com.example.m_1_16_recycler_view.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.m_1_16_recycler_view.entity.Camera

@JsonClass(generateAdapter = true)
data class CameraDto(
    @Json(name = "full_name") override val full_name: String,
    @Json(name = "id") override val id: Int,
    @Json(name = "name") override val name: String,
    @Json(name = "rover_id") override val rover_id: Int
) : Camera