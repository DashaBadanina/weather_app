package com.example.weatherapp.domane.di

import android.content.Context
import com.example.weatherapp.App
import com.example.weatherapp.data.network.OpenWeatherApi
import com.example.weatherapp.domane.repository.CityRepository
import com.example.weatherapp.domane.repository.WeatherRepository
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)
@Singleton
interface AppComponent {

    fun getApi(): OpenWeatherApi
    fun getContext(): Context
    fun getWeatherRepository(): WeatherRepository
    fun getCityRepository(): CityRepository
}