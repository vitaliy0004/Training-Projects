package com.example.dependency_injection.entity

interface Bike {
    val logo: String
    val wheels: List<Wheel>
    val frame: Frame
}