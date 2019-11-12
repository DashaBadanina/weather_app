package com.example.weatherapp.domane.usecase

import com.example.weatherapp.domane.repository.CityRepository
import com.example.weatherapp.domane.repository.WeatherRepository
import javax.inject.Inject

class CityInteractor @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) {

    fun getCities() = cityRepository
        .getAll()

}