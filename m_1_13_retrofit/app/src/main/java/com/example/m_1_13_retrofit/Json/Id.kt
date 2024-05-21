package com.example.m_1_13_retrofit.Json

import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String
)