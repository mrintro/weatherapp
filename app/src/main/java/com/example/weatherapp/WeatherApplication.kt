package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.component.ApplicationComponent
import com.example.weatherapp.di.component.DaggerApplicationComponent

class WeatherApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(applicationContext)
    }
}