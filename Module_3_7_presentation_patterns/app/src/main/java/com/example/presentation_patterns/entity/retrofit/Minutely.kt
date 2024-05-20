package com.example.presentation_patterns.entity.retrofit

import com.example.presentation_patterns.data.retrofit.dto.ValuesDto
interface Minutely {
    val time: String
    val values: ValuesDto
}