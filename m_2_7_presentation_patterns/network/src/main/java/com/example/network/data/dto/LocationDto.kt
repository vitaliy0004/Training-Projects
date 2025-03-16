package com.example.network.data.dto


import com.example.network.entity.Location
import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name") override val name: String
) : Location