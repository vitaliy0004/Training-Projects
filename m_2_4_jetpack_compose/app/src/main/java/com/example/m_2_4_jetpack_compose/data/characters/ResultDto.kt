package com.example.m_2_4_jetpack_compose.data.characters

import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("created") val created: String,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("gender") val gender: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("location") val location: LocationDto,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: OriginDto,
    @SerializedName("species") val species: String,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)