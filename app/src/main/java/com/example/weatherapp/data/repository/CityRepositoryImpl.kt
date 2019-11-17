package com.example.weatherapp.data.repository

import com.example.weatherapp.data.bd.dao.CityDao
import com.example.weatherapp.data.entity.SearchCity
import com.example.weatherapp.data.mapper.CityMapper
import com.example.weatherapp.data.model.City
import com.example.weatherapp.domane.repository.CityRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val dataSource: CityDao,
    private val cityMapper: CityMapper
) : CityRepository {

    override fun getAll(): Observable<List<City>> {
        return dataSource.getCities()
    }

    override fun saveAll(cities: List<City>): Observable<List<City>> {
        return Completable.fromCallable { dataSource.insertCities(cities) }
            .andThen(Observable.just(cities))
    }

    override fun save(searchCity: SearchCity): Observable<List<City>> {
        val cityList = listOf(cityMapper.mapImpl(searchCity))
        return Completable.fromCallable { dataSource.insertCities(cityList) }
            .andThen(Observable.just(cityList))
    }

    override fun delete(cityId: Long): Completable {
        return Completable.fromCallable { dataSource.delete(cityId) }
    }
}