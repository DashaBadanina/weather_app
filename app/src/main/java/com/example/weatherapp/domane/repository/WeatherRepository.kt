package com.example.weatherapp.domane.repository

import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.data.entity.Weather
import io.reactivex.Observable

interface WeatherRepository {

    fun getCurrentWeather(cityId : Long): Observable<Weather>

    fun getForecast(cityId : Long): Observable<Forecast>
}