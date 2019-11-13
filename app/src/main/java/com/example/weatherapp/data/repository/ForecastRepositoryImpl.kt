package com.example.weatherapp.data.repository

import com.example.weatherapp.data.bd.dao.ForecastDao
import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.network.OpenWeatherApi
import com.example.weatherapp.data.model.mapper.ForecastMapper
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.domane.repository.ForecastRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi,
    private val dataSource: ForecastDao,
    private val forecastMapper: ForecastMapper
    ) : ForecastRepository {

    override fun get(cityId: Long): Observable<ForecastModel> {
        return Observable.zip(
            api.getForecast(cityId),
            api.getCurrentWeather(cityId),
            BiFunction { forecast: Forecast, weather :Weather  ->
                forecastMapper.mapImpl(Pair(forecast, weather)) }
            )
            .flatMap {
                save(it)
            }
    }

    override fun save(forecast: ForecastModel): Observable<ForecastModel> {
        return Completable.fromCallable { dataSource.insertForecast(forecast) }
            .andThen(Observable.just(forecast))
    }
}