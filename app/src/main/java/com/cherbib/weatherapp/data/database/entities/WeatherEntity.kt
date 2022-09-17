
package com.cherbib.weatherapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cherbib.weatherapp.data.domain.Weather

/*
Just an example to set architecture (not final entity)
 */
// Todo implement real entity
@Entity
data class WeatherEntity constructor(
    @PrimaryKey
    val id: Long,
    val cityName: String,
    val longitude: String,
    val latitude: String,
    val tempMax: Double,
    val tempMin: Double
)

/**
 * Map DatabaseWeather to domain entities
 */
fun List<WeatherEntity>.asDomainModel(): List<Weather> {
    return map {
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
