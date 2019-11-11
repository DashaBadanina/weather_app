package com.example.weatherapp.data.repository

import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.network.OpenWeatherApi
import com.example.weatherapp.domane.repository.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi
) : WeatherRepository {

    override fun getCurrentWeather(cityId: Long): Observable<Weather> {
        return api.getCurrentWeather(cityId)
    }

    override fun getForecast(cityId: Long): Observable<Forecast> {
        return api.getForecast(cityId)
    }
}