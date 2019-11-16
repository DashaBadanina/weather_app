package com.example.weatherapp.domane.usecase

import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.domane.repository.CityRepository
import com.example.weatherapp.domane.repository.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject

class CityInteractor @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) {

    fun getCities() : Observable<List<City>> {
        return cityRepository.getAll()
    }

    fun getCurrentWeather(cityId: Long): Observable<WeatherModel> {
        return weatherRepository.get(cityId)
    }
}