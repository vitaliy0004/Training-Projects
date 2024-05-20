package com.example.module_2_13.Json

import com.google.gson.annotations.SerializedName


data class Timezone(
    @SerializedName("description")val description: String,
    @SerializedName("offset")val offset: String
)