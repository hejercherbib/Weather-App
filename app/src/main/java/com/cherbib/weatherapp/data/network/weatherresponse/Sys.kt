package com.cherbib.weatherapp.data.network.weatherresponse

import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)
