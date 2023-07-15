package com.example.weatherapp.di.component

import android.content.Context
import com.example.weatherapp.MainActivity
import com.example.weatherapp.di.annotations.ApplicationScope
import com.example.weatherapp.di.module.ApplicationModule
import com.example.weatherapp.di.module.NetworkModule
import com.example.weatherapp.ui.WeatherFragment
import com.example.weatherapp.viewmodel.GlobalViewModel
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(weatherFragment: WeatherFragment)

    fun inject(globalViewModel: GlobalViewModel)

}