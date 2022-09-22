package com.cherbib.weatherapp.data.domain

import com.cherbib.weatherapp.data.database.entities.CityEntity
import com.cherbib.weatherapp.data.database.entities.SavedCityEntity

/**
 * City //Just example to set  architecture
 */
data class City(
    val id: Int,
    val city: String,
    val lat: Double,
    val lng: Double,
    val country: String,
    val population: Long
)

/**
 * Convert domain model to database objects
 */
fun City.asDatabaseModel(): CityEntity {
    return CityEntity(
        id = this.id,
        city = this.city,
        lat = this.lat,
        lng = this.lng,
        country = this.country,
        population = this.population
    )
}

fun City.asSavedEnityDatabaseModel(): SavedCityEntity {
    return SavedCityEntity(
        id = this.id,
        city = this.city,
        lat = this.lat,
        lng = this.lng,
        country = this.country,
        population = this.population
    )
}
