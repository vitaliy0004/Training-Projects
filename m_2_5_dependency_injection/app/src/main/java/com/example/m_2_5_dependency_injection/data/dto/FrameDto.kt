package com.example.m_2_5_dependency_injection.data.dto

import com.example.m_2_5_dependency_injection.entity.Frame

data class FrameDto(
    override val number: Int,
    override val color: Int
) : Frame