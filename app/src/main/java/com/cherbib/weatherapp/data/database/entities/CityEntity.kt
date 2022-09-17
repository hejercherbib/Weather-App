
package com.cherbib.weatherapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cherbib.weatherapp.data.domain.City

/*
Just an example to set architecture (not final entity)
 */
// Todo implement real entity
@Entity
data class CityEntity constructor(
    @PrimaryKey
    val id: Long,
    val name: String,
    val longitude: String,
    val latitude: String,
    val temp: String
)

/**
 * Map DatabaseCities to domain entities
 */
fun List<CityEntity>.asDomainModel(): List<City> {
    return map {
        City(
            id = it.id,
            name = it.name,
            latitude = it.latitude,
            longitude = it.longitude,
            temp = it.temp
        )
    }
}
