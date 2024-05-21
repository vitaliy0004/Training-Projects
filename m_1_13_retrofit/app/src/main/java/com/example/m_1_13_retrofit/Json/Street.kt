package com.example.m_1_13_retrofit.Json

import com.google.gson.annotations.SerializedName

data class Street(
    @SerializedName("name") val name: String,
    @SerializedName("number") val number: Int
)