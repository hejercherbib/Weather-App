package com.cherbib.weatherapp.ui.mycitiesweather.addcity // ktlint-disable filename

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cherbib.weatherapp.data.domain.City
import com.cherbib.weatherapp.databinding.ItemCityBinding

public class SavedCityAdapter(private val adapterOnClick: AdapterOnClick) :
    ListAdapter<City, SavedCityAdapter.ViewHolder>(
        CityDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCityBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), adapterOnClick)
    }

    class ViewHolder(
        private val binding: ItemCityBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City, callback: AdapterOnClick) {
            with(binding) {
                binding.txvCityName.text = city.city
                binding.txvCityCountry.text = city.country
                binding.imgDelete.setOnClickListener {
                    callback.onDeleteClick(city)
                }
            }
        }
    }
}

private class CityDiffCallback : DiffUtil.ItemCallback<City>() {

    override fun areItemsTheSame(
        oldItem: City,
        newItem: City
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: City,
        newItem: City
    ): Boolean {
        return oldItem.id == newItem.id
    }
}

interface AdapterOnClick {
    fun onDeleteClick(item: City)
}
