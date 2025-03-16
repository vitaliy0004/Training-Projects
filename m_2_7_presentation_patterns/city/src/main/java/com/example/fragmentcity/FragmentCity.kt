package com.example.fragmentcity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aconst.AppConstants.GET_CITY
import com.example.aconst.AppConstants.LAST_CITY
import com.example.city.R
import com.example.city.databinding.FragmentCityBinding
import com.example.core.view_model.ViewModuleDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentCity : Fragment() {
    private var _binding: FragmentCityBinding? = null
    private lateinit var prefrs: SharedPreferences.Editor
    private val binding get() = _binding!!

    private val viewModule: ViewModuleDatabase by viewModels()
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
            getCityList()
        }
    }

    private suspend fun getCityList() {
        viewModule.allCity.collect {
            it.forEach { cityTemperature ->
                viewModule.listCity.add(cityTemperature.city)
            }
            val adapter = AdapterDatabase(
                { city -> onClick(city) },
                viewModule.listCity
            )
            binding.recyclerView.adapter = adapter
        }
    }

    private fun onClick(city: String) {
        prefrs.putString(LAST_CITY, city).apply()
        findNavController().navigate(R.id.action_fragmentCity_to_navigation)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}