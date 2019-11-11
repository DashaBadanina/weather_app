package com.example.weatherapp.domane.usecase

import com.example.weatherapp.domane.repository.WeatherRepository
import javax.inject.Inject

class WeatherInteractor @Inject constructor(
    private val repository: WeatherRepository
) {

    fun getWeather(cityId : Long) = repository.getCurrentWeather(cityId)
    fun getForecast(cityId : Long) = repository.getForecast(cityId)
}