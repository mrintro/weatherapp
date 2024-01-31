package com.example.weatherapp.repository

import android.util.Log
import androidx.room.util.query
import androidx.room.withTransaction
import arrow.core.Either
import com.example.weatherapp.data.WeatherDatabase
import com.example.weatherapp.model.mapper.mapToCurrentWeather
import com.example.weatherapp.utils.APIEndPoints
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.networkBoundResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

class ProjectRepository(
    private val service: APIService,
    private val weatherDatabase: WeatherDatabase
) {

    val weatherDao = weatherDatabase.weatherDao()
    val url = Constants.BASE_URL + APIEndPoints.WEATHER
    suspend fun getWeather() = Either.catch {
        Log.d("TAG", "url is $url")
        service.getWeather(url, "Bengaluru", "9b8cb8c7f11c077f8c4e217974d9ee40")
    }

    suspend fun getWeatherOffline() = networkBoundResource(
        query = {
            weatherDao.getCurrentWeather()
        },
        fetch = {
            service.getWeather(url, "Bengaluru", "9b8cb8c7f11c077f8c4e217974d9ee40")
        },
        saveFetchResult = {
            weatherDatabase.withTransaction {
                weatherDao.deleteCurrentWeather()
                weatherDao.insertCurrentWeatherData(it.mapToCurrentWeather())
            }
        }
    )

}