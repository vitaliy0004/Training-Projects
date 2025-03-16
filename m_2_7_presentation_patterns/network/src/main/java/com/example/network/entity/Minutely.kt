package com.example.network.entity

interface Minutely {
    val time: String
    val values: com.example.network.data.dto.ValuesDto
}