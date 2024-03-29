package com.example.weatherapp.data.network

import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.data.entity.SearchCityResult
import com.example.weatherapp.data.entity.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("weather")
    fun getCurrentWeather(
        @Query("id") cityId: Long,
        @Query("units") units: String = NetworkConfig.TEMP_UNITS,
        @Query("APPID") appid: String = NetworkConfig.APPID
    ): Observable<Weather>

    @GET("forecast")
    fun getForecast(
        @Query("id") cityId: Long,
        @Query("units") units: String = NetworkConfig.TEMP_UNITS,
        @Query("APPID") appid: String = NetworkConfig.APPID
    ): Observable<Forecast>

    @GET("find")
    fun getSearchCitiesResult(
        @Query("q") cityName: String,
        @Query("units") units: String = NetworkConfig.TEMP_UNITS,
        @Query("type") type: String = NetworkConfig.SEARCH_STRATEGY_TYPE,
        @Query("APPID") appid: String = NetworkConfig.APPID
    ): Observable<SearchCityResult>
}