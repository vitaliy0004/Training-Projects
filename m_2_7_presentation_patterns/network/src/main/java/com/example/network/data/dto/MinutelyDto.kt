package com.example.network.data.dto


import com.example.network.entity.Minutely
import com.google.gson.annotations.SerializedName

data class MinutelyDto(
    @SerializedName("time") override val time: String,
    @SerializedName("values") override val values: ValuesDto
) : Minutely