package com.example.network.data.dto


import com.example.network.entity.Timer
import com.google.gson.annotations.SerializedName

data class TimerDto(
    @SerializedName("location") override val location: LocationDto,
    @SerializedName("timelines") override val timelines: TimelinesDto
) : Timer