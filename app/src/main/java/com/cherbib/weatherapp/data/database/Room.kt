
package com.cherbib.weatherapp.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.* // ktlint-disable no-wildcard-imports
import com.cherbib.weatherapp.data.database.entities.CityEntity
import com.cherbib.weatherapp.data.database.entities.WeatherEntity

@Dao
interface CityDao {
    @Query("select * from cityentity")
    fun getCities(): LiveData<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cities: List<CityEntity>)
}

@Dao
interface WeatherDao {
    @Query("select * from weatherentity")
    fun getWeather(): LiveData<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cities: List<WeatherEntity>)
}

@Database(
    entities = [
        CityEntity::class,
        WeatherEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
    abstract val weatherDao: WeatherDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "WeatherAppDB"
            ).build()
        }
    }
    return INSTANCE
}
