package com.example.weatherapp.model.weatherresponse


import androidx.annotation.Keep

@Keep
data class Wind(
    val deg: Int?,
    val speed: Double?
)