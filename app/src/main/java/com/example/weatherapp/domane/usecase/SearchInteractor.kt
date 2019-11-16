package com.example.weatherapp.domane.usecase

import com.example.weatherapp.data.entity.SearchCity
import com.example.weatherapp.data.mapper.CityMapper
import com.example.weatherapp.data.model.City
import com.example.weatherapp.domane.repository.CityRepository
import com.example.weatherapp.domane.repository.SearchRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val searchRepository: SearchRepository,
    private val cityRepository: CityRepository
) {

    fun searchCities(cityName: String): Observable<List<SearchCity>> {
        return Observable.zip(
            searchRepository
                .get(cityName)
                .map { it.list },
            cityRepository.getAll(),
            BiFunction { searchCityList, cityList ->
                searchCityList
                    .filter {
                        !cityList.any { city -> city.id == it.id }
                    }
            })
    }

    fun saveCity(searchCity: SearchCity, cityName: String): Observable<List<SearchCity>> {
        return Observable
            .just(searchCity)
            .flatMap {
                cityRepository.save(it)
            }
            .flatMap { searchCities(cityName) }
    }
}