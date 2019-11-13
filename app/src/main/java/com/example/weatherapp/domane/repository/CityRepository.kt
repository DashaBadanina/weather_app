package com.example.weatherapp.domane.repository

import com.example.weatherapp.data.model.CityModel
import io.reactivex.Completable
import io.reactivex.Observable

interface CityRepository {

    fun getAll(): Observable<List<CityModel>>

    fun saveAll(cityModels: List<CityModel>): Completable
}