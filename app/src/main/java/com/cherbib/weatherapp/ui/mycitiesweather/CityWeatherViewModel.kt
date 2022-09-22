package com.cherbib.weatherapp.ui.mycitiesweather

import android.app.Application
import androidx.lifecycle.*
import com.cherbib.weatherapp.data.database.WeatherDatabase.Companion.getDatabase
import com.cherbib.weatherapp.data.domain.City
import com.cherbib.weatherapp.data.repository.CityRepository
import com.cherbib.weatherapp.data.repository.SavedCityRepository
import com.cherbib.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class CityWeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherRepository = WeatherRepository(getDatabase(application))
    private val cityRepository = CityRepository(getDatabase(application))
    val allCities = cityRepository.cities
    val weathers = weatherRepository.savedCityWeathers
    private val savedCityRepository = SavedCityRepository(getDatabase(application))
    val allSavedCities = savedCityRepository.cities

    fun insertCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            savedCityRepository.insertCity(city)
        }
        refreshDataFromRepository(city)
    }
    fun deleteCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            savedCityRepository.deleteCity(city)
        }
    }

    private fun refreshDataFromRepository(city: City) {
        viewModelScope.launch {
            try {
                weatherRepository.getWeather(city.lat, city.lng)
            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CityWeatherViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CityWeatherViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
