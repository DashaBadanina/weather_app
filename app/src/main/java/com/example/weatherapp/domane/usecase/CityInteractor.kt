package com.example.weatherapp.domane.usecase

import com.example.weatherapp.domane.repository.CityRepository
import javax.inject.Inject

class CityInteractor @Inject constructor(
    private val repository: CityRepository
) {

    fun getCities() = repository.getCities()
}