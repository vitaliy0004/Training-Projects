package com.example.m_2_6_background_works

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class ViewModule : ViewModel() {
    var allMillis = 0L
    val dateFormat = SimpleDateFormat("dd-MM-yy")
    val timeFrom = SimpleDateFormat("HH:mm")
    val calendarMadrid: Calendar = Calendar.getInstance()
    var region = ""
    var city = ""
    fun city() {
        //установили город
        timeFrom.timeZone = TimeZone.getTimeZone("$region/$city")
        calendarMadrid.timeZone = TimeZone.getTimeZone("$region/$city")
    }

    fun info() {
        allMillis = calendarMadrid.timeInMillis
    }
}