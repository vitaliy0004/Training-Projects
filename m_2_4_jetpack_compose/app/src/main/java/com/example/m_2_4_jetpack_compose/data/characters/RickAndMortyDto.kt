package com.example.m_2_4_jetpack_compose.data.characters

import com.google.gson.annotations.SerializedName


data class RickAndMortyDto(
    @SerializedName("info") val info: InfoDto,
    @SerializedName("results") val results: List<ResultDto>
)