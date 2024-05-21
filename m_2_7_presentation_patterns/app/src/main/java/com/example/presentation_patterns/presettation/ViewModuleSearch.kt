package com.example.presentation_patterns.presettation

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel

class ViewModuleSearch : ViewModel() {
    var lastCity: Set<String> = mutableSetOf<String>()
    val allCity = mutableListOf<String>("NewYorkCity", "LosAngeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "SanAntonio", "SanDiego", "Barcelona", "Xiantao", "Madrid", "London", "Paris", "Dubai", "Tokyo", "Amsterdam", "Berlin", "Rome", "Singapore", "Munich", "Milan", "Seoul", "Dublin", "Osaka", "HongKong", "Vienna", "Los Angeles", "Lisbon", "Prague", "Sydney", "Istanbul", "Melbourne", "Orlando", "Frankfurt", "Kyoto", "Taipei", "Florence", "Toronto", "Athens", "Zurich", "Bangkok", "Miami", "LasVegas", "Kuala Lumpur", "Stockholm", "Brussels", "TelAviv", "SanFrancisco", "Shanghai", "Warsaw", "Guangzhou", "Copenhagen", "Nice", "Washington", "Budapest", "Shenzhen", "Vancouver", "Lima", "Porto", "Edinburgh", "Delhi", "Tbilisi", "Santiago", "Vilnius", "Zhuhai", "Muscat", "Moscow", "SaintPetersburg", "Kazan", "NizhnyNovgorod", "Yekaterinburg", "Novosibirsk", "Tomsk", "Vladivostok", "Petrozavodsk", "Samara", "Perm", "NaberezhnyeChelny", "Ussuriysk", "Sochi", "Obninsk", "Arzamas", "Chelyabinsk", "Kaliningrad", "Tyumen", "Krasnoyarsk")
    val filter = mutableListOf<String>()
    fun filter(world: String) {
        filter.clear()
        allCity.forEach {
            val counterText = world.length
            if (world == it.filterIndexed { index, c -> index < counterText })
                filter.add(it)
        }
    }
    fun adapter(context: Context, int: Int, list: List<String>): ArrayAdapter<String> {
        return ArrayAdapter(context, int, list)
    }
}
