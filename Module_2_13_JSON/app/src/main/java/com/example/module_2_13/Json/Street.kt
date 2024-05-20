package com.example.module_2_13.Json

import com.google.gson.annotations.SerializedName


data class Street(
    @SerializedName("name")val name: String,
    @SerializedName("number")val number: Int
)