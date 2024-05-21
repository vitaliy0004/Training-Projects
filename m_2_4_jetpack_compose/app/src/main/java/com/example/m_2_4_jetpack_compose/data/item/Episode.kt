package com.example.m_2_4_jetpack_compose.data.item

import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("air_date") val airDate: String,
    @SerializedName("episode") val episode: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)
