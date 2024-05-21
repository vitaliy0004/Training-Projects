package com.example.background_works

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.background_works.databinding.FragmentTimerBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar


class FragmentTimer : Fragment() {
    private lateinit var prefs: SharedPreferences.Editor
    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!
    private val viewModule: ViewModule by viewModels()
    private val workManager = OneTimeWorkRequestBuilder<WorkerTimer>().build()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimerBinding.inflate(layoutInflater)
        prefs = requireContext().getSharedPreferences(Name.WORKER, Context.MODE_PRIVATE).edit()
        return binding.root
    }
    @SuppressLint("NewApi", "WrongConstant", "SuspiciousIndentation", "EnqueueWork")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_timer2_to_activityFragment)
        }
        //устанавливаем дату
        binding.date.setOnClickListener {
            val dataDialog = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText(resources.getString(R.string.set_an_alarm_clock))
                .setSelection(Calendar.getInstance().timeInMillis)
                .build()
            dataDialog.addOnPositiveButtonClickListener { timeInMillis ->
                binding.dateText.text = viewModule.dateFormat.format(timeInMillis)
                viewModule.calendarMadrid.set(
                    viewModule.dateFormat.calendar.get(Calendar.YEAR),
                    viewModule.dateFormat.calendar.get(Calendar.MONTH),
                    viewModule.dateFormat.calendar.get(Calendar.DAY_OF_MONTH)
                )
            }
            dataDialog.show(childFragmentManager, "dataPiker")
        }
        //устанавливаем время
        binding.time.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText(resources.getString(R.string.time_clock))
                .build().apply {
                    addOnPositiveButtonClickListener {
                        viewModule.calendarMadrid.set(Calendar.HOUR_OF_DAY, this.hour)
                        viewModule.calendarMadrid.set(Calendar.MINUTE, this.minute)
                        binding.timeText.text =
                            viewModule.timeFrom.format(viewModule.calendarMadrid.timeInMillis)
                    }
                }
            picker.show(childFragmentManager, "TimePicker")
        }
        //запускаем WORKER
        binding.start.setOnClickListener {
            if (binding.dateText.text == resources
                    .getString(R.string.choose_day) || binding.timeText.text == resources.getString(
                    R.string.choose_time
                )
            )
                Toast.makeText(requireContext(), resources.getText(R.string.input_params), Toast.LENGTH_SHORT).show()
            else {
                viewModule.info()
                prefs.putLong(Name.MILLISECONDS, viewModule.allMillis).apply()
                WorkManager.getInstance(requireContext()).enqueue(workManager)
                WorkManager.getInstance(requireContext()).beginWith(workManager).enqueue()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}