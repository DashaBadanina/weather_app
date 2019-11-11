package com.example.weatherapp.data.network

import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.data.entity.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    fun getCurrentWeather(
        @Query("cityId") cityId: Long,
        @Query("units") units: String = NetworkConfig.TEMP_UNITS,
        @Query("APPID") appid: String = NetworkConfig.APPID
    ): Observable<Weather>

    @GET("forecast")
    fun getForecast(
        @Query("cityId") cityId: Long,
        @Query("units") units: String = NetworkConfig.TEMP_UNITS,
        @Query("APPID") appid: String = NetworkConfig.APPID
    ): Observable<Forecast>
}