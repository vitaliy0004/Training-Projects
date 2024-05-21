package com.example.m_2_5_dependency_injection.data

import com.example.m_2_5_dependency_injection.data.dto.WheelDto
import com.example.m_2_5_dependency_injection.entity.Wheel
import kotlin.random.Random

class FabriceWheel {
    fun wheel(): Wheel {
        return WheelDto(Random.nextInt(0, 99999))
    }
}