package com.example.module_2_13.Json

import com.google.gson.annotations.SerializedName


data class Coordinates(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude")val longitude: String
)