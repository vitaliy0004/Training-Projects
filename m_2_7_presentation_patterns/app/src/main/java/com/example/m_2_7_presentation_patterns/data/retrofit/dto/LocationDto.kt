package com.example.m_2_7_presentation_patterns.data.retrofit.dto

import com.example.m_2_7_presentation_patterns.entity.retrofit.Location
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name") override val name: String
) : Location