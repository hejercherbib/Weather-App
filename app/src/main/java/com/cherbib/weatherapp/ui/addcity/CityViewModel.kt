package com.cherbib.weatherapp.ui.addcity

import android.app.Application
import androidx.lifecycle.*
import com.cherbib.weatherapp.data.database.WeatherDatabase.Companion.getDatabase
import com.cherbib.weatherapp.data.domain.City
import com.cherbib.weatherapp.data.repository.CityRepository
import com.cherbib.weatherapp.data.repository.SavedCityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(application: Application) : AndroidViewModel(application) {

    private val cityRepository = CityRepository(getDatabase(application))
    val allCities = cityRepository.cities

    private val savedCityRepository = SavedCityRepository(getDatabase(application))
    val allSavedCities = savedCityRepository.cities

    init {
        // Todo implement fetching weather based on saved cities
    }

    fun insertCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            savedCityRepository.insertCity(city)
        }
    }
    fun deleteCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            savedCityRepository.deleteCity(city)
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
