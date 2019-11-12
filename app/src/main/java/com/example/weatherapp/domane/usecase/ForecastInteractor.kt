package com.example.weatherapp.domane.usecase

import com.example.weatherapp.domane.repository.ForecastRepository
import com.example.weatherapp.domane.repository.WeatherRepository
import javax.inject.Inject

class ForecastInteractor @Inject constructor(
    private val forecastRepository: ForecastRepository,
    private val weatheRepository: WeatherRepository
) {

    fun getForecast(cityId: Long) =
        forecastRepository
            .get(cityId)
}