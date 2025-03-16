package com.example.fragmentcity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_city.databinding.ItemListCityBinding

class AdapterDatabase(
    private val onClick: (String) -> Unit,
    private val city: MutableSet<String>
) : RecyclerView.Adapter<CityDatabaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityDatabaseViewHolder {
        val binding =
            ItemListCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityDatabaseViewHolder(binding)
    }

    override fun getItemCount(): Int = city.size

    override fun onBindViewHolder(holder: CityDatabaseViewHolder, position: Int) {
        val index = city.toList()[position]
        holder.binding.city.text = index
        holder.binding.root.setOnClickListener {
            onClick(index)
        }
    }
}

class CityDatabaseViewHolder(val binding: ItemListCityBinding) :
    RecyclerView.ViewHolder(binding.root)
