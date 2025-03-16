package com.example.network.data.dto


import com.example.network.entity.Timelines
import com.google.gson.annotations.SerializedName

data class TimelinesDto(
    @SerializedName("minutely") override val minutely: List<MinutelyDto>
) : Timelines