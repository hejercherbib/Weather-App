package com.cherbib.weatherapp.data.network

import com.cherbib.weatherapp.data.devbyteviewer.network.NetworkWeatherResponse
import retrofit2.http.GET

interface WeatherService {
    @GET("weather")
    suspend fun getWeather(): NetworkWeatherResponse
}
