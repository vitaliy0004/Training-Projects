package com.example.m_2_7_presentation_patterns.entity.retrofit

import com.example.m_2_7_presentation_patterns.data.retrofit.dto.MinutelyDto

interface Timelines {
    val minutely: List<MinutelyDto>
}