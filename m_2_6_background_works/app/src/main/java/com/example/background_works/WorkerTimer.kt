package com.example.background_works

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class WorkerTimer(
    private val context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {
    private lateinit var alarmIntent: PendingIntent
    private var alarmManager: AlarmManager? = null
    private val viewModule = ViewModule()
    private val prefr = context.getSharedPreferences(Name.WORKER, Context.MODE_PRIVATE)
    private val calendar: Calendar = Calendar.getInstance()
    private var dataBack = SimpleDateFormat("dd-MM-yy HH:mm")
    override suspend fun doWork(): Result {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        dataBack.timeZone = TimeZone.getTimeZone("${viewModule.region}/${viewModule.city}")
        calendar.timeInMillis = prefr.getLong(Name.MILLISECONDS, 0L)
        App().createNotification(dataBack.format(calendar.timeInMillis), context)
        Log.d("main12:", "${calendar.timeInMillis} ss ${dataBack.format(calendar.timeInMillis)}")
        Log.d("main12:", ", ${dataBack.format(calendar.timeInMillis)}")
        alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        )
        val outputData = workDataOf(Name.TIME to alarmManager)
        return Result.success(outputData)
    }
}


