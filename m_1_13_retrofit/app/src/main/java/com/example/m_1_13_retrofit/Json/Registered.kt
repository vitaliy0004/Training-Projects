package com.example.m_1_13_retrofit.Json

import com.google.gson.annotations.SerializedName

data class Registered(
    @SerializedName("age") val age: Int,
    @SerializedName("date") val date: String
)