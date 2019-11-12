package com.example.weatherapp.data.repository

import com.example.weatherapp.data.bd.dao.WeatherDao
import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.network.OpenWeatherApi
import com.example.weatherapp.domane.repository.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi,
    private val dataSource: WeatherDao
) : WeatherRepository {

    override fun get(cityId: Long): Observable<Weather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(weather: Weather): Observable<Weather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}