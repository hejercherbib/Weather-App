package com.cherbib.weatherapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.cherbib.weatherapp.data.database.AppDatabase
import com.cherbib.weatherapp.data.database.entities.asDomainModel
import com.cherbib.weatherapp.data.devbyteviewer.network.asDatabaseModel
import com.cherbib.weatherapp.data.domain.Weather
import com.cherbib.weatherapp.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching weather from the network and storing them on local db
 */
// Todo put some forecast duration constrains when fetching from db
class WeatherRepository(private val database: AppDatabase) {
    val weather: LiveData<List<Weather>> = Transformations.map(database.weatherDao.getWeather()) {
        it.asDomainModel()
    }

    suspend fun getWeather() {
        withContext(Dispatchers.IO) {
            val weather = RetrofitClient.retrofitService.getWeather()
            database.weatherDao.insertAll(weather.asDatabaseModel())
        }
    }
}
