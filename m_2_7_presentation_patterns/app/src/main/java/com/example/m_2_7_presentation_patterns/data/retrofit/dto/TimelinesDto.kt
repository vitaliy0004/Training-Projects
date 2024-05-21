package com.example.m_2_7_presentation_patterns.data.retrofit.dto

import com.example.m_2_7_presentation_patterns.entity.retrofit.Timelines
import com.google.gson.annotations.SerializedName

data class TimelinesDto(
    @SerializedName("minutely") override val minutely: List<MinutelyDto>
) : Timelines