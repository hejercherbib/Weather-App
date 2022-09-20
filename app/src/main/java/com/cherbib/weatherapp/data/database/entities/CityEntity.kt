
package com.cherbib.weatherapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cherbib.weatherapp.data.domain.City

/*
Just an example to set architecture (not final entity)
 */
// Todo implement real entity
@Entity(tableName = "city_entity")
data class CityEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val city: String,
    val lat: Double,
    val lng: Double,
    val country: String,
    val population: Long

)

/**
 * Map DatabaseCities to domain entities
 */
fun List<CityEntity>.asDomainModel(): List<City> {
    return map {
        City(
            id = it.id,
            city = it.city,
            lat = it.lat,
            lng = it.lng,
            country = it.country,
            population = it.population
        )
    }
}
