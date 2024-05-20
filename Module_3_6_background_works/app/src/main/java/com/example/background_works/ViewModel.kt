package com.example.background_works

import android.util.Log
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class ViewModule : ViewModel() {
    var allMillis = 0L
    val dateFormat = SimpleDateFormat("dd-MM-yy")
    val timeFrom = SimpleDateFormat("HH:mm")
    var dataAndTime = SimpleDateFormat("dd-MM-yy HH:mm")
    val calendarMadrid:Calendar = Calendar.getInstance()
    var region = ""
    var city = ""
  fun city(){
      //установили город
      timeFrom.timeZone = TimeZone.getTimeZone("$region/$city")
      calendarMadrid.timeZone  = TimeZone.getTimeZone("$region/$city")
  }
    fun info() {
        allMillis = calendarMadrid.timeInMillis
        Log.d("main1:", "$allMillis ss ${dataAndTime.format(allMillis)}")
    }
}