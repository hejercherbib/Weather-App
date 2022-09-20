package com.cherbib.weatherapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.cherbib.weatherapp.data.database.WeatherDatabase
import com.cherbib.weatherapp.data.database.entities.asDomainModel
import com.cherbib.weatherapp.data.domain.WeatherDomain
import com.cherbib.weatherapp.data.network.RetrofitClient
import com.cherbib.weatherapp.data.network.weatherresponse.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching weather from the network and storing them on local db
 */
// Todo put some forecast duration constrains when fetching from db
class WeatherRepository(private val database: WeatherDatabase) {
    val savedCityWeathers: LiveData<List<WeatherDomain>> = Transformations.map(database.weatherDao().getWeather()) {
        it.asDomainModel()
    }

    suspend fun getWeather(lat: Double, long: Double) {
        withContext(Dispatchers.IO) {
            val weatherResponse = RetrofitClient.retrofitService.getWeatherByLocation(lat.toString(), long.toString())
            database.weatherDao().insert(weatherResponse.asDatabaseModel())
        }
    }
}
