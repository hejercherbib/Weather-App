package com.cherbib.weatherapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.* // ktlint-disable no-wildcard-imports
import com.cherbib.weatherapp.data.database.entities.WeatherEntity

@Dao
interface WeatherDao {
    @Query("select * from weatherentity")
    fun getWeather(): LiveData<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherEntity: WeatherEntity)
}
