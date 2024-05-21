package com.example.m_1_16_recycler_view.entity

import com.example.m_1_16_recycler_view.data.CameraDto
import com.example.m_1_16_recycler_view.data.RoverDto

interface Photos {
    val camera: CameraDto
    val earthDate: String
    val id: Int
    val img_src: String
    val rover: RoverDto
    val sol: Int
}