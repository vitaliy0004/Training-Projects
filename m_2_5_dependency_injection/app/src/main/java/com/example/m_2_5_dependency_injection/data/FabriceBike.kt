package com.example.m_2_5_dependency_injection.data

import com.example.m_2_5_dependency_injection.data.dto.BikeDto


class FabriceBike(
    private val wheel: FabriceWheel,
    private val frame: FabriceFrame
) {
    fun build(logo: String, color: Int, number: Int): BikeDto {
        val frames = frame.create(number, color)
        val wheelsBack = wheel.wheel()
        val wheelsFront = wheel.wheel()
        return BikeDto(logo, listOf(wheelsFront, wheelsBack), frames)
    }

}