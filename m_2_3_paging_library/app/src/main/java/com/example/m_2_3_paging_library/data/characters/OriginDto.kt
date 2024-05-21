package com.example.m_2_3_paging_library.data.characters

import com.google.gson.annotations.SerializedName

data class OriginDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)