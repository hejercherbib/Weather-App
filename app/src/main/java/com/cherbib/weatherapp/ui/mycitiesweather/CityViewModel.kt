package com.cherbib.weatherapp.ui.mycitiesweather

import android.app.Application
import androidx.lifecycle.*
import com.cherbib.weatherapp.data.database.WeatherDatabase.Companion.getDatabase
import com.cherbib.weatherapp.data.domain.City
import com.cherbib.weatherapp.data.repository.CityRepository
import com.cherbib.weatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class CityViewModel(application: Application) : AndroidViewModel(application) {

    private val cityRepository = CityRepository(getDatabase(application))
    private val weatherRepository = WeatherRepository(getDatabase(application))

    val allCities = cityRepository.cities
    val weathers = weatherRepository.savedCityWeathers

    private val _allCities = MutableLiveData<List<City>>()

    init {
        // Todo implement fetching weather based on saved cities
    }

    // Todo  delete this just used for test
    fun insertCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            cityRepository.insertCity(city)
        }
        refreshDataFromRepository(city)
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
            if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CityViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
