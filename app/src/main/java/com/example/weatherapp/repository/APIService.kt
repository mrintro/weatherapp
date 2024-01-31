package com.example.weatherapp.repository

import com.example.weatherapp.model.weatherresponse.WeatherResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getWeather(
        @Url url: String,
        @Query("q") area: String,
        @Query("APPID") appId: String,
        @Query("units") units: String = "Metric"
    ): WeatherResponse

}