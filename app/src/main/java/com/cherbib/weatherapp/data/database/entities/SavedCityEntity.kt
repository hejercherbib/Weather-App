
package com.cherbib.weatherapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cherbib.weatherapp.data.domain.City

@Entity(tableName = "saved_city_entity")
data class SavedCityEntity constructor(
    @PrimaryKey
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
fun List<SavedCityEntity>.asDomainModel(): List<City> {
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
