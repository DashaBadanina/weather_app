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

    override fun saveCities(cities: List<City>): Completable {
        return Completable.fromCallable {
            dataSource.insertCities(cities)
        }
    }

    override fun getCities(): Observable<List<City>> {
        return dataSource.getCities()
    }
}