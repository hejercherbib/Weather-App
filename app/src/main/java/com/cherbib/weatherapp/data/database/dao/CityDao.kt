package com.cherbib.weatherapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cherbib.weatherapp.data.database.entities.CityEntity

@Dao
interface CityDao {
    @Query("select * from city_entity")
    fun getCities(): LiveData<List<CityEntity>>

    @Insert
    suspend fun insertCity(city: CityEntity)

    @Delete
    fun deleteCity(city: CityEntity)
}
