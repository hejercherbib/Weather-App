
package com.cherbib.weatherapp.data.domain

/**
 * Weather //Just example to set  architecture
 */
data class Weather(
    val id: Long,
    val cityName: String,
    val latitude: String,
    val longitude: String,
    val tempMin: Double,
    val tempMax: Double
)
