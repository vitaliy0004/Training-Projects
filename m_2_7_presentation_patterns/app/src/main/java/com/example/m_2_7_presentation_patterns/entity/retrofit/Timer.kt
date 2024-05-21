package com.example.m_2_7_presentation_patterns.entity.retrofit

import com.example.m_2_7_presentation_patterns.data.retrofit.dto.LocationDto
import com.example.m_2_7_presentation_patterns.data.retrofit.dto.TimelinesDto

interface Timer {
    val location: LocationDto
    val timelines: TimelinesDto
}