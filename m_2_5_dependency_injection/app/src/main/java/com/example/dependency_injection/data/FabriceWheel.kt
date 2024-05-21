package com.example.dependency_injection.data

import com.example.dependency_injection.data.dto.WheelDto
import com.example.dependency_injection.entity.Wheel
import kotlin.random.Random

class FabriceWheel {
    fun wheel(): Wheel {
        return WheelDto(Random.nextInt(0, 99999))
    }
}