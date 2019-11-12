package com.example.weatherapp.data.repository

import com.example.weatherapp.data.bd.dao.CityDao
import com.example.weatherapp.data.entity.City
import com.example.weatherapp.domane.repository.CityRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val dataSource: CityDao
): CityRepository {

    override fun saveAll(cities: List<City>): Observable<List<City>> {
        return dataSource.insertCities(cities)

    }

    override fun getAll(): Observable<List<City>> {
        return dataSource.getCities()
    }
}