package com.example.dependency_injection.data

import com.example.dependency_injection.data.dto.FrameDto
import com.example.dependency_injection.entity.Frame


class FabriceFrame {
    fun create(number: Int, color: Int): Frame {
        return FrameDto(number, color)
    }
}