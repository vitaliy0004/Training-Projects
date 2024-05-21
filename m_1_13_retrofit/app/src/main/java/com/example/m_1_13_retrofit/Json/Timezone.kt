package com.example.m_1_13_retrofit.Json

import com.google.gson.annotations.SerializedName


data class Timezone(
    @SerializedName("description")val description: String,
    @SerializedName("offset")val offset: String
)