package com.example.m_1_16_recycler_view.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.example.m_1_16_recycler_view.entity.PhotoSputnik

@JsonClass(generateAdapter = true)
data class PhotoSputnikDto(
    @Json(name = "photos") override val photos: List<PhotosDto>
) : PhotoSputnik
