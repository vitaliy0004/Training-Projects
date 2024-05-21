package com.example.m_2_7_presentation_patterns.entity.retrofit

import com.example.m_2_7_presentation_patterns.data.retrofit.dto.ValuesDto

interface Minutely {
    val time: String
    val values: ValuesDto
}