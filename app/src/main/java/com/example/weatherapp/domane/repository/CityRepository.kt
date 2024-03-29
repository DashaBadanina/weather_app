package com.example.weatherapp.domane.repository

import com.example.weatherapp.data.entity.SearchCity
import com.example.weatherapp.data.model.City
import io.reactivex.Completable
import io.reactivex.Observable

interface CityRepository {

    fun getAll(): Observable<List<City>>

    fun saveAll(cities: List<City>): Observable<List<City>>

    fun save(searchCity: SearchCity): Observable<List<City>>

    fun delete(cityId: Long): Completable
}