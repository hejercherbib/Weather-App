package com.cherbib.weatherapp.data.network.weatherresponse

import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class Clouds(
    val all: Int
)
