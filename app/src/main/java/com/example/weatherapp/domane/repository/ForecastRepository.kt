package com.example.weatherapp.domane.repository

import com.example.weatherapp.data.entity.Forecast
import io.reactivex.Observable

interface ForecastRepository {

    fun get(cityId: Long): Observable<Forecast>

    fun save(forecast: Forecast): Observable<Forecast>
}