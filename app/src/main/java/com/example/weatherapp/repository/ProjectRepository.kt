package com.example.weatherapp.repository

import arrow.core.Either
import com.example.weatherapp.utils.APIEndPoints
import com.example.weatherapp.utils.Constants

class ProjectRepository(
    private val service: APIService
) {

    suspend fun getWeather() = Either.catch {
        service.getWeather(Constants.BASE_URL + APIEndPoints.WEATHER, "Bengaluru", "9b8cb8c7f11c077f8c4e217974d9ee40")
    }

}