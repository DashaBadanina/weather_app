package com.example.weatherapp.data.repository

import com.example.weatherapp.data.bd.dao.ForecastDao
import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.data.network.OpenWeatherApi
import com.example.weatherapp.domane.repository.ForecastRepository
import io.reactivex.Observable
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi,
    private val dataSource: ForecastDao
) : ForecastRepository {

    override fun get(cityId: Long): Observable<Forecast> {
        return api
            .getForecast(cityId)
            .flatMap {
                dataSource.insertForecast(it)
            }
    }

    override fun save(forecast: Forecast): Observable<Forecast> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}