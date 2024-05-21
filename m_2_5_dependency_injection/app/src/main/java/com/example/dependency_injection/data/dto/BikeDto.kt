package com.example.dependency_injection.data.dto

import com.example.dependency_injection.entity.Bike
import com.example.dependency_injection.entity.Frame
import com.example.dependency_injection.entity.Wheel

data class BikeDto(
    override val logo: String,
    override val wheels: List<Wheel>,
    override val frame: Frame
) : Bike