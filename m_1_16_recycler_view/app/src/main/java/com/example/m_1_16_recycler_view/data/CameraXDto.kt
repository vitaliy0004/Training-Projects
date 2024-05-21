package com.example.m_1_16_recycler_view.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.m_1_16_recycler_view.entity.CameraX

@JsonClass(generateAdapter = true)
data class CameraXDto(
    @Json(name = "full_name") override val full_name: String,
    @Json(name = "name") override val name: String
) : CameraX