package com.cherbib.weatherapp.data.network.weatherresponse // ktlint-disable filename

import com.cherbib.weatherapp.data.database.entities.WeatherEntity
import com.cherbib.weatherapp.data.domain.WeatherDomain
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherRes(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun WeatherRes.asDomainModel(): WeatherDomain {
    return WeatherDomain(
        id = this.sys.id,
        cityName = this.name,
        latitude = this.coord.lat,
        longitude = this.coord.lon,
        tempMax = this.main.temp_max,
        tempMin = this.main.temp_min,
        temp = this.main.temp,
        country = this.sys.country,
        sunrise = this.sys.sunrise,
        sunset = this.sys.sunset,
        feels_like = this.main.feels_like,
        humidity = this.main.humidity,
        pressure = this.main.pressure,
        description = this.weather[0].description,
        icon = this.weather[0].icon
    )
}

/**
 * Convert Network results to database objects
 */
fun WeatherRes.asDatabaseModel(): WeatherEntity {
    return WeatherEntity(
        id = this.sys.id,
        cityName = this.name,
        latitude = this.coord.lat,
        longitude = this.coord.lon,
        tempMax = this.main.temp_max,
        tempMin = this.main.temp_min,
        temp = this.main.temp,
        country = this.sys.country,
        sunrise = this.sys.sunrise,
        sunset = this.sys.sunset,
        feels_like = this.main.feels_like,
        humidity = this.main.humidity,
        pressure = this.main.pressure,
        description = this.weather[0].description,
        icon = this.weather[0].icon
    )
}
