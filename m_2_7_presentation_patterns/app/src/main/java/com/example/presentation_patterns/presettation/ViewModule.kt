package com.example.presentation_patterns.presettation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.presentation_patterns.data.retrofit.dto.TimerDto
import com.example.presentation_patterns.domain.Repository

class ViewModule(private val repository: Repository) : ViewModel() {
    var weather: TimerDto? = null
    var listCity = mutableSetOf<String>()
    var allCity = mutableSetOf<String>()
    var copy = false
    fun filterCity(location: String) {
        val filterCity = mutableSetOf<String>()
        if (listCity.size > 3) {
            filterCity.add(listCity.toList()[2])
            filterCity.add(listCity.toList()[1])
            filterCity.add(location)
            listCity = filterCity
        } else listCity.add(location)
    }

    suspend fun city(city: String) {
        weather = repository.cityWeather(city)
    }

    fun location(city: String): String {
        var counterBreak = false
        var counter = 0
        city.forEach {
            if (it == ',') counterBreak = true
            if (!counterBreak) counter++
        }
        return city.filterIndexed { index, c -> index < counter }
    }

    fun dataText(time: String): String {
        val data = time.replace('T', ' ', true)
        return data.filterIndexed { index, c -> index < 16 }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    fun checkCopy(location: String) {
        allCity.forEach {
            if (it == location) copy = true
        }

    }
}


