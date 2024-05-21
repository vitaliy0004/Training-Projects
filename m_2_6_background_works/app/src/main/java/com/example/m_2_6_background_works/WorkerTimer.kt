package com.example.m_2_6_background_works

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
        alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        )
        val outputData = workDataOf(Name.TIME to alarmManager)
        return Result.success(outputData)
    }
}


