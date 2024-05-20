package com.example.presentation_patterns.data.retrofit.dto

import com.example.presentation_patterns.entity.retrofit.Location
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name") override val name: String
) : Location