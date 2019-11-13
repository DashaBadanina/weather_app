package com.example.weatherapp.domane.usecase

import com.example.weatherapp.data.entity.SearchCityResult
import com.example.weatherapp.domane.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val searchRepository: SearchRepository
) {

    fun searchCitis(cityName: String): Observable<SearchCityResult> {
        return searchRepository
            .searchCity(cityName)
    }
}