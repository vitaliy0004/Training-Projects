package com.example.modul_2_9

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.modul_2_9.databinding.FragmentTestResultBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TestResult : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentTestResultBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentTestResultBinding.inflate(inflater)

        ObjectAnimator.ofArgb(binding.textView,"textColor",
            Color.parseColor("#B83986"),Color.parseColor("#FF0000FF")
        ).apply {
                duration = 4000
                interpolator = AccelerateDecelerateInterpolator()
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.RESTART
                start()
            }
        ObjectAnimator.ofArgb(binding.button,"textColor",
            Color.parseColor("#FFEB3B"),Color.parseColor("#FF000000")
        ).apply {
            duration = 4000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 6
            repeatMode = ObjectAnimator.RESTART
            start()
        }

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_test_result_to_test)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = param1
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}