package com.example.weatherapp.domane.usecase

import com.example.weatherapp.data.entity.SearchCity
import com.example.weatherapp.data.entity.SearchCityResult
import com.example.weatherapp.data.mapper.CityMapper
import com.example.weatherapp.data.model.City
import com.example.weatherapp.domane.repository.CityRepository
import com.example.weatherapp.domane.repository.SearchRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val searchRepository: SearchRepository,
    private val cityRepository: CityRepository,
    private val cityMapper: CityMapper
) {

    fun searchCities(cityName: String): Observable<List<SearchCity>> {
        return searchRepository
            .searchCity(cityName)
            .map { it.list }
    }

    fun saveCity(searchCity: SearchCity): Observable<List<City>> {
        return Observable
            .just(searchCity)
            .map { cityMapper.mapImpl(it) }
            .map { listOf(it) }
            .flatMap {
                cityRepository.saveAll(it)
            }
    }
}