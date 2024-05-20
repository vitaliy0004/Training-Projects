package com.example.module_2_13.Json

import com.google.gson.annotations.SerializedName


data class RandomUser(
    @SerializedName("results") val results: List<Result>,
    @SerializedName("info") val info: Info

)
