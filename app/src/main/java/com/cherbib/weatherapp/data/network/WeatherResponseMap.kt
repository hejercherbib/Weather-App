
package com.cherbib.weatherapp.data.devbyteviewer.network

import com.cherbib.weatherapp.data.database.entities.WeatherEntity
import com.cherbib.weatherapp.data.domain.Weather
import com.squareup.moshi.JsonClass

/**
Wether response mapping
 */
@JsonClass(generateAdapter = true)
data class NetworkWeatherResponse(val weather: List<NetworkWeather>)

/**
 * weather can be displayed.
 */
@JsonClass(generateAdapter = true)
data class NetworkWeather(
    val id: Long,
    val cityName: String,
    val longitude: String,
    val latitude: String,
    val tempMax: Double,
    val tempMin: Double
)

/**
 * Convert Network results to domain objects
 */
fun NetworkWeatherResponse.asDomainModel(): List<Weather> {
    return weather.map {
        Weather(
            id = it.id,
            cityName = it.cityName,
            latitude = it.latitude,
            longitude = it.longitude,
            tempMax = it.tempMax,
            tempMin = it.tempMin
        )
    }
}

/**
 * Convert Network results to database objects
 */
fun NetworkWeatherResponse.asDatabaseModel(): List<WeatherEntity> {
    return weather.map {
        WeatherEntity(
            id = it.id,
            cityName = it.cityName,
            latitude = it.latitude,
            longitude = it.longitude,
            tempMax = it.tempMax,
            tempMin = it.tempMin
        )
    }
}
