package com.example.weatherapp.domane.repository

import com.example.weatherapp.data.model.ForecastModel
import io.reactivex.Completable
import io.reactivex.Observable

interface ForecastRepository {

    fun get(cityId: Long): Observable<ForecastModel>

    fun save(forecast: ForecastModel): Observable<ForecastModel>

    fun delete(cityId: Long): Completable
}