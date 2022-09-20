
package com.cherbib.weatherapp.data.domain

/**
 * Weather */
data class WeatherDomain(
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
