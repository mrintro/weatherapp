package com.example.weatherapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeather(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var temp: String,
    var areaName: String,
    var lat: Float,
    var long: Float,
    var feelsLike: String
) {
    constructor(): this(0, "", "", 1.2f, 2.1f, "")
}
