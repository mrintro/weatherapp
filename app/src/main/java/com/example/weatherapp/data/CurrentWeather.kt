package com.example.weatherapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeather(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val temp: Float,
    val areaName: String,
    val lat: Float,
    val long: Float,
    val feelsLike: String
)
