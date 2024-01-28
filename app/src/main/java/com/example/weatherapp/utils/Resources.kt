package com.example.weatherapp.utils

sealed class Resources<T>(
    data: T? = null,
    throwable: Throwable? = null
) {
    class Success<T>(data: T): Resources<T>(data)
    class Loading<T>(data: T? = null): Resources<T>(data)
    class Error<T>(throwable: Throwable?, data: T? = null): Resources<T>(data, throwable)
}

