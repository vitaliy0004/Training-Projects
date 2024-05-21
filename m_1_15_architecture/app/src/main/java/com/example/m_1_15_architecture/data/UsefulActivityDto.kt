package com.example.m_1_15_architecture.data

import com.example.m_1_15_architecture.entity.UsefulActivity
import com.google.gson.annotations.SerializedName

data class UsefulActivityDto(
    @SerializedName("activity") override val activity: String,
    @SerializedName("type") override val type: String,
    @SerializedName("participants") override val participants: Int,
    @SerializedName("price") override val price: Double,
    @SerializedName("link") override val link: String,
    @SerializedName("key") override val key: String,
    @SerializedName("accessibility") override val accessibility: Double
) : UsefulActivity
