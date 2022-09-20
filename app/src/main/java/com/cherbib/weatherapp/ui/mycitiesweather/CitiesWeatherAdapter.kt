package com.cherbib.weatherapp.ui.mycitiesweather // ktlint-disable filename

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cherbib.weatherapp.data.domain.WeatherDomain
import com.cherbib.weatherapp.databinding.ItemCityBinding

class CityWeatherAdapter :
    ListAdapter<WeatherDomain, CityWeatherAdapter.ViewHolder>(
        CityDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCityBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemCityBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: WeatherDomain) {
            with(binding) {
                binding.txvCityName.text = weather.cityName
                binding.txvCityTemp.text = weather.temp.toString()
            }
        }
    }
}

private class CityDiffCallback : DiffUtil.ItemCallback<WeatherDomain>() {

    override fun areItemsTheSame(
        oldItem: WeatherDomain,
        newItem: WeatherDomain
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WeatherDomain,
        newItem: WeatherDomain
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
