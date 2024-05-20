package com.example.presentation_patterns.data.retrofit.dto

import com.example.presentation_patterns.entity.retrofit.Timelines
import com.google.gson.annotations.SerializedName

data class TimelinesDto(
    @SerializedName("minutely") override val minutely: List<MinutelyDto>
) : Timelines