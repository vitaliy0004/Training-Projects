package com.example.presentation_patterns.entity.retrofit

import com.example.presentation_patterns.data.retrofit.dto.LocationDto
import com.example.presentation_patterns.data.retrofit.dto.TimelinesDto

interface Timer {
    val location: LocationDto
    val timelines: TimelinesDto
}