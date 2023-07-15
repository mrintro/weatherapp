package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.model.weatherresponse.WeatherResponse
import com.example.weatherapp.repository.ProjectRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GlobalViewModel : ViewModel(), DefaultLifecycleObserver {

    @Inject
    lateinit var projectRepository: ProjectRepository

    val weatherTemperature =  MutableLiveData<Double?>(null)

    val showLoading = MutableLiveData<Boolean>(true)

    val showError = MutableLiveData<Boolean>(false)

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getWeatherData()
    }

    private fun getWeatherData() {
        Log.d("TAG", "Method called")
        viewModelScope.launch {
            projectRepository.getWeather().fold({
                showError.value = true
                Log.d("TAG", "Error true ${it.message}")
            }, {

                showLoading.value = false
                it.main?.temp?.let {temperature ->
                    Log.d("TAG", "Error false $temperature")
                    weatherTemperature.value = temperature
                    showError.value = false
                } ?: kotlin.run{
                    showError.value = true
                }
            })
        }
    }

}