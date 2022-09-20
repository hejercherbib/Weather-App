
package com.cherbib.weatherapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cherbib.weatherapp.data.domain.WeatherDomain

/*
Just an example to set architecture (not final entity)
 */
@Entity
data class WeatherEntity constructor(
    @PrimaryKey
    val id: Int,
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val tempMin: Double,
    val tempMax: Double,
    val temp: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val description: String,
    val icon: String
)

/**
 * Map DatabaseWeather to domain entities
 */
fun List<WeatherEntity>.asDomainModel(): List<WeatherDomain> {
    return map {
        WeatherDomain(
            id = it.id,
            cityName = it.cityName,
            latitude = it.latitude,
            longitude = it.longitude,
            tempMax = it.tempMax,
            tempMin = it.tempMin,
            temp = it.temp,
            country = it.country,
            sunrise = it.sunrise,
            sunset = it.sunset,
            feels_like = it.feels_like,
            humidity = it.humidity,
            pressure = it.pressure,
            description = it.description,
            icon = it.icon
        )
    }
}
