package com.cherbib.weatherapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.cherbib.weatherapp.data.database.WeatherDatabase
import com.cherbib.weatherapp.data.database.entities.asDomainModel
import com.cherbib.weatherapp.data.domain.City
import com.cherbib.weatherapp.data.domain.asSavedEnityDatabaseModel

class SavedCityRepository(private val database: WeatherDatabase) {

    val cities: LiveData<List<City>> = Transformations.map(database.savedCityDao().getCities()) {
        it.asDomainModel()
    }

    suspend fun insertCity(city: City) {
        database.savedCityDao().insertCity(city.asSavedEnityDatabaseModel())
    }
    suspend fun deleteCity(city: City) = database.savedCityDao().deleteCity(city.asSavedEnityDatabaseModel())
}
