package com.example.dependency_injection.data.dto

import com.example.dependency_injection.entity.Wheel

data class WheelDto(
    override val wheel: Int
) : Wheel