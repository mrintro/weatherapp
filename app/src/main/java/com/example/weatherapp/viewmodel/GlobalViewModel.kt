package com.example.weatherapp.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.weatherapp.repository.ProjectRepository
import javax.inject.Inject

class GlobalViewModel : ViewModel(), DefaultLifecycleObserver {

    @Inject
    lateinit var projectRepository: ProjectRepository





}