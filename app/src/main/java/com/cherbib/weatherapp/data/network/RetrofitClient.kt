package com.cherbib.weatherapp.data.network

import com.cherbib.weatherapp.utils.BaseUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// not working yet just to set architecture
object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val retrofitService = retrofit.create(WeatherService::class.java)
}
