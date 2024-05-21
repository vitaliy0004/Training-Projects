package com.example.m_1_13_retrofit.Json

import com.google.gson.annotations.SerializedName

data class RandomUser(
    @SerializedName("results") val results: List<Result>,
    @SerializedName("info") val info: Info

)
