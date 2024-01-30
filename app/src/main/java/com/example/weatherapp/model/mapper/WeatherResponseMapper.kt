package com.example.weatherapp.model.mapper

import com.example.weatherapp.data.CurrentWeather
import com.example.weatherapp.model.weatherresponse.WeatherResponse

fun WeatherResponse.mapToCurrentWeather() = CurrentWeather(
    temp = main?.temp?.toString()?.let { "$it degree" }  ?: "NA",
    areaName = name ?: "NA",
    feelsLike = main?.feels_like.toString() ?: "NA",
    lat = coord?.lat?.toFloat() ?: 0F,
    long = coord?.lon?.toFloat() ?: 0F
)