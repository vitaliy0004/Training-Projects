package com.example.background_works

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.background_works.databinding.FragmentActivityBinding


class ActivityFragment : Fragment() {
    private var _binding: FragmentActivityBinding? = null
    private val binding get() = _binding!!
    val viewModule: ViewModule by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // можно ввести    Region: Europe     City: Madrid
        binding.textRegion.setOnClickListener {
            binding.region.text = binding.textRegion.text
            if (binding.city.text != "") binding.timer.isEnabled = true
        }
        binding.textCity.setOnClickListener {
            binding.city.text = binding.textCity.text
            if (binding.region.text != "") binding.timer.isEnabled = true
        }
        binding.timer.setOnClickListener {
            viewModule.city = binding.city.text.toString()
            viewModule.region = binding.region.text.toString()
            viewModule.city()
            findNavController().navigate(R.id.action_activityFragment_to_timer2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}