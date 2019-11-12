package com.example.weatherapp.domane.repository

import com.example.weatherapp.data.entity.Weather
import io.reactivex.Observable

interface WeatherRepository {

    fun get(cityId: Long): Observable<Weather>

    fun save(weather: Weather): Observable<Weather>
}