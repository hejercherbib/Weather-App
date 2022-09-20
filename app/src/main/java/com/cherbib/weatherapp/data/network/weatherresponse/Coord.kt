package com.cherbib.weatherapp.data.network.weatherresponse

import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class Coord(
    val lat: Double,
    val lon: Double
)
