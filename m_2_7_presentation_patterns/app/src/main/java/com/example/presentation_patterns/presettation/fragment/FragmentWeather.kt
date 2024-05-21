package com.example.presentation_patterns.presettation.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation_patterns.R
import com.example.presentation_patterns.data.databas.CityDao
import com.example.presentation_patterns.databinding.FragmentWeatherBinding
import com.example.presentation_patterns.presettation.Adapter
import com.example.presentation_patterns.presettation.App
import com.example.presentation_patterns.presettation.FactoryViewModel
import com.example.presentation_patterns.presettation.MainActivity
import com.example.presentation_patterns.presettation.ViewModule
import com.example.presentation_patterns.presettation.ViewModuleDatabase
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject


@Suppress("UNREACHABLE_CODE")
class FragmentWeather : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val viewModuleDatabase: ViewModuleDatabase by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val cityDao: CityDao = (activity?.application as App).db.cityDao()
                return ViewModuleDatabase(cityDao) as T
            }
        }
    }
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: FactoryViewModel
    private val viewModule: ViewModule by viewModels { factory }

    private lateinit var adapter: Adapter
    private lateinit var prefrs: SharedPreferences.Editor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(layoutInflater)
        (activity as MainActivity).appComponent.inject(this)
        prefrs = context?.getSharedPreferences(GET_CITY, Context.MODE_PRIVATE)?.edit()!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getPrefers = context?.getSharedPreferences(GET_CITY, Context.MODE_PRIVATE)
        val city =
            getPrefers?.getString(LAST_CITY, resources.getString(R.string.you_have_not_used))!!

        binding.greetings.back.setOnClickListener {
            if (
                getPrefers.getInt(SECOND_FRAGMENT, 0) == 1
            ) findNavController().navigate(R.id.action_fragmentWeather_to_search)
            else findNavController().navigate(R.id.action_fragmentWeather_to_city)
        }
        lifecycleScope.launch {
            binding.greetings.delete.isClickable = true
            // если пользователь ввел некоректный город или нет интернета
            try {
                viewModule.city(city)
                binding.progress.visibility = View.INVISIBLE
            } catch (e: Exception) {
                viewModule.weather = null
            }
            if (viewModule.weather == null) {
                // проверка в чем ошибка
                if (!viewModule.isOnline(requireContext())) {
                    viewModule.allCity = getPrefers.getStringSet(ALL_CITY, viewModule.allCity)!!
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
                binding.progress.visibility = View.INVISIBLE
            } else {
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
                viewModule.listCity = getPrefers.getStringSet(LIST_CITY, mutableSetOf())!!
                viewModule.filterCity(location)
                prefrs.putStringSet(LIST_CITY, viewModule.listCity).apply()
                viewModule.allCity = getPrefers.getStringSet(ALL_CITY, viewModule.allCity)!!
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
        }
    }
    private suspend fun deleteButton(location: String) {
        lifecycleScope.launch { delete(location) }
        viewModule.allCity.remove(location)
        Log.d("main:", location)
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