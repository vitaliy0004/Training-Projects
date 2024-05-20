package com.example.presentation_patterns.presettation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation_patterns.data.databas.CityDao
import com.example.presentation_patterns.data.databas.CityTemperature
import com.example.presentation_patterns.data.retrofit.dto.MinutelyDto
import com.example.presentation_patterns.data.retrofit.dto.ValuesDto
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewModuleDatabase(private val timerDao: CityDao) : ViewModel() {
    val listCity = mutableSetOf<String>()
    val allCity = this.timerDao.getCity()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000L),
            initialValue = emptyList()
        )

    suspend fun saveTemperature(city: String): List<MinutelyDto> {
        val listMinutelyDto = mutableListOf<MinutelyDto>()
        val listCityTemperature = timerDao.getHistory(city).toMutableList()
        listCityTemperature.forEach {
            val values = ValuesDto(it.temperature.toDouble())
            listMinutelyDto.add(MinutelyDto(it.data, values))
        }
        return listMinutelyDto
    }

    fun saveCity(cityAndData: String, city: String, data: String, temperature: String) {
        viewModelScope.launch {
            timerDao.addCityTemperature(
                CityTemperature(
                    cityAndData = cityAndData,
                    city = city,
                    data = data,
                    temperature = temperature
                )
            )
        }
    }

    fun delete(cityAndData: String, city: String, data: String, temperature: String) {
        viewModelScope.launch {
            allCity.value.let {
                timerDao.delete(
                    CityTemperature(
                        cityAndData = cityAndData,
                        city = city,
                        data = data,
                        temperature = temperature
                    )
                )
            }
        }
    }
}