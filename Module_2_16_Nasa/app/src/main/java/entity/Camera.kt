package entity

import com.squareup.moshi.Json

interface Camera {
    val full_name: String
    val id: Int
    val name: String
    val rover_id: Int
}