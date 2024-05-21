package com.example.module_2_16_architectur.data

import com.example.module_2_16_architectur.entity.UsefulActivity
import com.google.gson.annotations.SerializedName

data class UsefulActivityDto (
    @SerializedName("activity") override val activity: String,
    @SerializedName("type")override val type: String,
    @SerializedName("participants")override val participants: Int,
    @SerializedName("price") override val price: Double,
    @SerializedName("link")override val link: String,
    @SerializedName("key") override val key: String,
    @SerializedName("accessibility")override val accessibility: Double
) : UsefulActivity
