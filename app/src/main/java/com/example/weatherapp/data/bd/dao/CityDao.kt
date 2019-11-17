package com.example.weatherapp.data.bd.dao

import androidx.room.*
import com.example.weatherapp.data.model.City
import io.reactivex.Observable

@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    fun getCities(): Observable<List<City>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities: List<City>)

    @Query("DELETE FROM cities WHERE id = :cityId")
    fun delete(cityId: Long)
}