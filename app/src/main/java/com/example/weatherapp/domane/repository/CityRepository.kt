package com.example.weatherapp.domane.repository

import com.example.weatherapp.data.entity.City
import io.reactivex.Completable
import io.reactivex.Observable

interface CityRepository {

    fun getCities(): Observable<List<City>>

    fun saveCities(cities: List<City>): Completable
}