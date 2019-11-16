package com.example.weatherapp.domane.repository

import com.example.weatherapp.data.entity.SearchCityResult
import io.reactivex.Observable

interface SearchRepository {

    fun get(cityName: String) : Observable<SearchCityResult>
}