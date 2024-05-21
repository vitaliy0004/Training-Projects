package com.example.m_2_3_paging_library.data.characters

import com.google.gson.annotations.SerializedName

data class RickAndMortyDto(
    @SerializedName("info") val info: InfoDto,
    @SerializedName("results") val results: List<ResultDto>
)