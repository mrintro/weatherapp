package com.example.weatherapp.di.module

import com.example.weatherapp.di.annotations.ApplicationScope
import com.example.weatherapp.repository.APIService
import com.example.weatherapp.repository.ProjectRepository
import com.example.weatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApplicationModule {

    @ApplicationScope
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @ApplicationScope
    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @ApplicationScope
    @Provides
    fun getProjectRepository(
        service: APIService
    ) = ProjectRepository(service)
}