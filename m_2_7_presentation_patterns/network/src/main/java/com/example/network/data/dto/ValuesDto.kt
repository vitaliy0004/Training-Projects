package com.example.network.data.dto


import com.example.network.entity.Values
import com.google.gson.annotations.SerializedName

data class ValuesDto(
    @SerializedName("temperature") override val temperature: Double,
) : Values