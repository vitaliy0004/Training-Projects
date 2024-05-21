package com.example.m_2_4_jetpack_compose.data.characters

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)