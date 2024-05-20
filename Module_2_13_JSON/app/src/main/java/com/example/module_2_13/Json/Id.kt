package com.example.module_2_13.Json

import com.google.gson.annotations.SerializedName


data class Id(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String
)