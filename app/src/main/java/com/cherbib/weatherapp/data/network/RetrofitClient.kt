package com.cherbib.weatherapp.data.network

import com.cherbib.weatherapp.utils.BaseUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// not working yet just to set architecture
object RetrofitClient {

    val httpClient = OkHttpClient.Builder().addInterceptor(QueryParameterAddInterceptor()).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(httpClient)
        .build()

    val retrofitService = retrofit.create(WeatherService::class.java)
}
