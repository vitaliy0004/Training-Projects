package com.example.presentation_patterns.data.retrofit.dto

import com.example.presentation_patterns.entity.retrofit.Timer
import com.google.gson.annotations.SerializedName

data class TimerDto(
    @SerializedName("location") override val location: LocationDto,
    @SerializedName("timelines") override val timelines: TimelinesDto
) : Timer