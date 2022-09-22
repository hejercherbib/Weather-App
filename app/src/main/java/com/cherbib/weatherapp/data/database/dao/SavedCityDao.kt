package com.cherbib.weatherapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cherbib.weatherapp.data.database.entities.SavedCityEntity

@Dao
interface SavedCityDao {
    @Query("select * from saved_city_entity")
    fun getCities(): LiveData<List<SavedCityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: SavedCityEntity)

    @Delete
    fun deleteCity(city: SavedCityEntity)
}
