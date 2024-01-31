package com.example.weatherapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * from current_weather")
    fun getCurrentWeather(): Flow<List<CurrentWeather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeatherData(currentWeather: CurrentWeather)

    @Query("DELETE FROM current_weather")
    suspend fun deleteCurrentWeather()

}