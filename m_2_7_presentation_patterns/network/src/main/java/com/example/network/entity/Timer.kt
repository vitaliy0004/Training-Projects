package com.example.network.entity

interface Timer {
    val location: com.example.network.data.dto.LocationDto
    val timelines: com.example.network.data.dto.TimelinesDto
}