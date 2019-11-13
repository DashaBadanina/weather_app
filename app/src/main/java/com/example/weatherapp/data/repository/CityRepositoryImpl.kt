package com.example.weatherapp.data.repository

import com.example.weatherapp.data.bd.dao.CityDao
import com.example.weatherapp.data.model.CityModel
import com.example.weatherapp.domane.repository.CityRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val dataSource: CityDao
): CityRepository {

    override fun getAll(): Observable<List<CityModel>> {
        return dataSource.getCities()
    }

    override fun saveAll(cityModels: List<CityModel>): Completable {
        return Completable.fromCallable {
            dataSource.insertCities(cityModels)
        }
    }
}