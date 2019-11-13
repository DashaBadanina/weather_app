package com.example.weatherapp.data.repository

import com.example.weatherapp.data.entity.SearchCityResult
import com.example.weatherapp.data.network.OpenWeatherApi
import com.example.weatherapp.domane.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi
) : SearchRepository {

    override fun searchCity(cityName: String): Observable<SearchCityResult> {
        return api.getSearchCitiesResult(cityName)
    }
}