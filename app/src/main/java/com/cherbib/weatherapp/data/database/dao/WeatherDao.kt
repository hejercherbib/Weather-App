package com.cherbib.weatherapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.* // ktlint-disable no-wildcard-imports
import com.cherbib.weatherapp.data.database.entities.WeatherEntity

@Dao
interface WeatherDao {
    @Query("select * from WeatherEntity")
    fun getWeather(): LiveData<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherEntity: WeatherEntity)

    @Query("delete from WeatherEntity where latitude=:latitude and longitude =:longitude")
    fun deleteWeather(latitude: Double, longitude: Double)
}
