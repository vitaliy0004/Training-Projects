package com.example.presentation_patterns.entity.retrofit

import com.example.presentation_patterns.data.retrofit.dto.MinutelyDto

interface Timelines {
    val minutely: List<MinutelyDto>
}