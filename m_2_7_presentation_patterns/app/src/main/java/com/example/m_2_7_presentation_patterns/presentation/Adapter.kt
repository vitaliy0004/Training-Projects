package com.example.m_2_7_presentation_patterns.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m_2_7_presentation_patterns.data.retrofit.dto.MinutelyDto
import com.example.m_2_7_presentation_patterns.databinding.ItemWeatherBinding

class Adapter(
    private val temperatureList: List<MinutelyDto>,
    private val viewModule: ViewModule
) :
    RecyclerView.Adapter<CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun getItemCount(): Int = temperatureList.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val index = temperatureList[position]
        with(holder.binding) {
            data.text = viewModule.dataText(index.time)
            temperature.text = index.values.temperature.toString()
            index.let {

            }
        }
    }
}

class CityViewHolder(val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root)
