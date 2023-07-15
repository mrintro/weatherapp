package com.example.weatherapp.model

interface APIResponse {

    fun isSuccess(): Boolean

    fun isError(): Boolean

}