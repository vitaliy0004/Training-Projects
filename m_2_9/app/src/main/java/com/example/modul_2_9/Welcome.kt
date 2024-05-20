package com.example.modul_2_9

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.modul_2_9.databinding.FragmentWelcomeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Welcome : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SimpleDateFormat")
    val dateFormat = SimpleDateFormat("dd-MM-yy")
    @SuppressLint("NewApi")
    val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_test)
        }
        binding.buttonDay.setOnClickListener {
            val dataDialog= MaterialDatePicker.Builder.datePicker()
               .setTitleText(resources.getString(R.string.indicate_your_date))
               .build()


            dataDialog.addOnPositiveButtonClickListener{ timeInMillis ->
                calendar.timeInMillis = timeInMillis
                Snackbar.make(binding.buttonDay, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
            }
            dataDialog.show(childFragmentManager, "dataPiker")
            }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}