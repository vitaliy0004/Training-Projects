package com.example.m_2_5_dependency_injection.data.dto

import com.example.m_2_5_dependency_injection.entity.Bike
import com.example.m_2_5_dependency_injection.entity.Frame
import com.example.m_2_5_dependency_injection.entity.Wheel

data class BikeDto(
    override val logo: String,
    override val wheels: List<Wheel>,
    override val frame: Frame
) : Bike