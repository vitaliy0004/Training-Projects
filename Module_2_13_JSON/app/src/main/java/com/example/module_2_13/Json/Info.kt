package com.example.module_2_13.Json

import com.google.gson.annotations.SerializedName


data class Info(
    @SerializedName("page")   val page: Int,
    @SerializedName("results") val results: Int,
    @SerializedName("seed")  val seed: String,
    @SerializedName("version") val version: String
)