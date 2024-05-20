package entity

import data.CameraDto
import data.RoverDto

interface Photos {
    val camera: CameraDto
    val earthDate: String
    val id: Int
    val img_src: String
    val rover: RoverDto
    val sol: Int
}