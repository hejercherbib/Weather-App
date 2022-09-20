package com.cherbib.weatherapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.cherbib.weatherapp.data.database.WeatherDatabase
import com.cherbib.weatherapp.data.database.entities.asDomainModel
import com.cherbib.weatherapp.data.domain.City
import com.cherbib.weatherapp.data.domain.asDatabaseModel

class CityRepository(private val database: WeatherDatabase) {

    val cities: LiveData<List<City>> = Transformations.map(database.cityDao().getCities()) {
        it.asDomainModel()
    }

    suspend fun insertCity(city: City) {
        database.cityDao().insertCity(city.asDatabaseModel())
    }
    suspend fun deleteCity(city: City) = database.cityDao().deleteCity(city.asDatabaseModel())
}
