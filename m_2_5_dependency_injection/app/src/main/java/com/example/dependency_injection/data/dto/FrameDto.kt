package com.example.dependency_injection.data.dto

import com.example.dependency_injection.entity.Frame

data class FrameDto(
    override val number: Int,
    override val color: Int
) : Frame