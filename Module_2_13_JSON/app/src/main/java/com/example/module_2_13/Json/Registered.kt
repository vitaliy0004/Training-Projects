package com.example.module_2_13.Json

import com.google.gson.annotations.SerializedName


data class Registered(
    @SerializedName("age")val age: Int,
    @SerializedName("date")val date: String
)