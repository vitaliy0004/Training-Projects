package com.example.presentation_patterns.data.retrofit.dto

import com.example.presentation_patterns.entity.retrofit.Minutely
import com.google.gson.annotations.SerializedName

data class MinutelyDto(
    @SerializedName("time") override val time: String,
    @SerializedName("values") override val values: ValuesDto
) : Minutely