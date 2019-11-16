package com.example.weatherapp.data.repository

import com.example.weatherapp.data.bd.dao.WeatherDao
import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.mapper.WeatherMapper
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.network.NetworkUtils
import com.example.weatherapp.data.network.OpenWeatherApi
import com.example.weatherapp.domane.repository.WeatherRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi,
    private val dataSource: WeatherDao,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {

    override fun get(cityId: Long): Observable<WeatherModel> {
        if (NetworkUtils.isNetworkAvailable()) {
            return api
                .getCurrentWeather(cityId)
                .map { weatherMapper.mapImpl(it) }
                .flatMap { save(it) }
        } else {
            return dataSource.getWeather(cityId)
        }
    }

    override fun save(weather: WeatherModel): Observable<WeatherModel> {
        return Completable.fromCallable { dataSource.insertWeather(weather) }
            .andThen(Observable.just(weather))
    }
}