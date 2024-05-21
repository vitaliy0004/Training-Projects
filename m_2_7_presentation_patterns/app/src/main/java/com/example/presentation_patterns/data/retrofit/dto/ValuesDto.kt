package com.example.presentation_patterns.data.retrofit.dto

import com.example.presentation_patterns.entity.retrofit.Values
import com.google.gson.annotations.SerializedName

data class ValuesDto(
    @SerializedName("temperature") override val temperature: Double,
) : Values