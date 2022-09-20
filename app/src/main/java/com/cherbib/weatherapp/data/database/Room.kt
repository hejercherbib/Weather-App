package com.cherbib.weatherapp.data.database // ktlint-disable filename

import android.content.Context
import androidx.room.* // ktlint-disable no-wildcard-imports
import com.cherbib.weatherapp.data.database.dao.CityDao
import com.cherbib.weatherapp.data.database.dao.WeatherDao
import com.cherbib.weatherapp.data.database.entities.CityEntity
import com.cherbib.weatherapp.data.database.entities.WeatherEntity

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(CityEntity::class, WeatherEntity::class), version = 1, exportSchema = false)
public abstract class WeatherDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                )
                    .createFromAsset("database/db_prepopulated_with_cities.db")
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
