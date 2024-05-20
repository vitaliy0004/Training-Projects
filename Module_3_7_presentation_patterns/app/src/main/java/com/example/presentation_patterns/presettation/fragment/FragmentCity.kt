package com.example.presentation_patterns.presettation.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation_patterns.R
import com.example.presentation_patterns.data.databas.CityDao
import com.example.presentation_patterns.databinding.FragmentCityBinding
import com.example.presentation_patterns.presettation.AdapterDatabase
import com.example.presentation_patterns.presettation.App
import com.example.presentation_patterns.presettation.ViewModuleDatabase
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class FragmentCity : Fragment() {
    private var _binding: FragmentCityBinding? = null
    private lateinit var prefrs: SharedPreferences.Editor
    private val binding get() = _binding!!
    private val viewModule: ViewModuleDatabase by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val cityDao: CityDao = (activity?.application as App).db.cityDao()
                return ViewModuleDatabase(cityDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityBinding.inflate(layoutInflater)
        prefrs = context?.getSharedPreferences(GET_CITY, Context.MODE_PRIVATE)?.edit()!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModule.allCity.collect {
                it.forEach { cityTemperature ->
                    viewModule.listCity.add(cityTemperature.city)
                }
                val adapter = AdapterDatabase({ city -> onClick(city) }, viewModule.listCity)
                binding.recyclerView.adapter = adapter
            }
        }
    }

    private fun onClick(city: String) {
        prefrs.putString(LAST_CITY, city).apply()
        prefrs.putInt(SECOND_FRAGMENT, 2).apply()
        findNavController().navigate(R.id.action_city_to_fragmentWeather2)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}