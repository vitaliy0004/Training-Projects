package com.example.m_2_5_dependency_injection.data

import com.example.m_2_5_dependency_injection.data.dto.FrameDto
import com.example.m_2_5_dependency_injection.entity.Frame


class FabriceFrame {
    fun create(number: Int, color: Int): Frame {
        return FrameDto(number, color)
    }
}