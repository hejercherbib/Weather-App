package com.cherbib.weatherapp.data.network

import com.cherbib.weatherapp.data.network.weatherresponse.WeatherRes
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getWeatherByLocation(
        @Query("lat")
        latitude: String,
        @Query("lon")
        longitude: String
    ): WeatherRes
}
