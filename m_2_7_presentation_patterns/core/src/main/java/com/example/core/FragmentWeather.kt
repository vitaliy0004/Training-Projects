package com.example.core

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.core.databinding.FragmentWeatherBinding
import kotlinx.coroutines.launch
import com.example.aconst.AppConstants.ALL_CITY
import com.example.aconst.AppConstants.GET_CITY
import com.example.aconst.AppConstants.LAST_CITY
import com.example.aconst.AppConstants.LIST_CITY
import com.example.core.navigation.NavigationRouter
import com.example.core.navigation.SomeViewModel
import com.example.core.view_model.ViewModule
import com.example.core.view_model.ViewModuleDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@Suppress("UNREACHABLE_CODE")
class FragmentWeather : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: FactoryViewModel

    @Inject
    lateinit var navRouter: NavigationRouter
    private val someViewModel: SomeViewModel by activityViewModels()

    private val viewModule: ViewModule by viewModels { factory }
    private lateinit var adapter: Adapter
    private lateinit var prefrs: SharedPreferences.Editor
    private lateinit var getPrefrs: SharedPreferences

    private val viewModuleDatabase: ViewModuleDatabase by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(layoutInflater)
        prefrs = context?.getSharedPreferences(GET_CITY, Context.MODE_PRIVATE)?.edit()!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navRouter.setViewModel(someViewModel)
        getPrefrs = requireContext().getSharedPreferences(GET_CITY, Context.MODE_PRIVATE)!!
        val city =
            getPrefrs.getString(LAST_CITY, resources.getString(R.string.you_have_not_used))!!
        binding.greetings.back.setOnClickListener { navRouter.navigateBack() }
        lifecycleScope.launch {
            loadWeatherInfo(city)
        }
    }

    private suspend fun loadWeatherInfo(city: String) {
        binding.greetings.delete.isClickable = true
        validateCityInput(city)
        if (viewModule.weather == null) {
            checkCityError(city)
            binding.progress.visibility = View.INVISIBLE
        } else {
            showNetworkWeather()
        }
    }

    private suspend fun validateCityInput(city: String) {
        try {
            viewModule.city(city)
            binding.progress.visibility = View.INVISIBLE
        } catch (e: Exception) {
            viewModule.weather = null
        }
    }

    private suspend fun checkCityError(city: String) {
        if (!viewModule.isOnline(requireContext())) {
            viewModule.allCity = getPrefrs.getStringSet(ALL_CITY, viewModule.allCity)!!
            viewModule.allCity.forEach { if (it == city) viewModule.copy = true }
            //если есть достаём данные из базы данных
            if (viewModule.copy) {
                val copyCity = viewModuleDatabase.saveTemperature(city)
                binding.greetings.city.text = city
                adapter = Adapter(copyCity, viewModule)
                binding.recyclerView.adapter = adapter
                viewModule.copy = false
                binding.greetings.delete.setOnClickListener {
                    lifecycleScope.launch {
                        deleteButton(city)
                    }
                }
            } else {
                binding.greetings.city.text = resources.getText(R.string.ups)
                binding.online.visibility = View.VISIBLE
            }
        } else binding.greetings.city.text = resources.getText(R.string.city_not_found)
    }

    private suspend fun showNetworkWeather() {
        val location = viewModule.location(viewModule.weather!!.location.name)
        binding.greetings.delete.setOnClickListener {
            lifecycleScope.launch {
                deleteButton(location)
            }
        }
        binding.greetings.city.text = location
        adapter = Adapter(viewModule.weather!!.timelines.minutely, viewModule)
        binding.recyclerView.adapter = adapter
        // как сохранять данные
        viewModule.listCity = getPrefrs.getStringSet(LIST_CITY, mutableSetOf())!!
        viewModule.filterCity(location)
        prefrs.putStringSet(LIST_CITY, viewModule.listCity).apply()
        viewModule.allCity = getPrefrs.getStringSet(ALL_CITY, viewModule.allCity)!!
        viewModule.checkCopy(location)
        if (!viewModule.copy) {
            saveCity(location)
            viewModule.allCity.add(location)
        } else {
            delete(location)
            saveCity(location)
        }
        prefrs.putStringSet(ALL_CITY, viewModule.allCity).apply()
    }


    private suspend fun deleteButton(location: String) {
        delete(location)
        viewModule.allCity.remove(location)
        viewModuleDatabase.listCity.remove(location)
        binding.greetings.delete.isClickable = false
        Toast.makeText(
            requireContext(),
            resources.getText(R.string.data_this_city),
            Toast.LENGTH_SHORT
        ).show()
    }

    private suspend fun delete(location: String) {
        viewModuleDatabase.saveTemperature(location).forEach {
            val cityAndData = "$location _ ${it.time}"
            viewModuleDatabase.delete(
                cityAndData,
                location,
                it.time,
                it.values.temperature.toString()
            )
        }
    }

    private fun saveCity(location: String) {
        viewModule.weather!!.timelines.minutely.forEach {
            val cityAndData = "$location _ ${it.time}"
            viewModuleDatabase.saveCity(
                cityAndData,
                location,
                it.time,
                it.values.temperature.toString()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}