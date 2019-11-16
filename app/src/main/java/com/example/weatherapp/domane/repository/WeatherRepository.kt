package com.example.weatherapp.domane.repository

import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.model.WeatherModel
import io.reactivex.Observable

interface WeatherRepository {

    fun get(cityId: Long): Observable<WeatherModel>

    fun save(weather: WeatherModel): Observable<WeatherModel>
}