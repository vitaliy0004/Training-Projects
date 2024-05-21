package com.example.m_1_13_retrofit.Json

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: Int,
    @SerializedName("seed") val seed: String,
    @SerializedName("version") val version: String
)