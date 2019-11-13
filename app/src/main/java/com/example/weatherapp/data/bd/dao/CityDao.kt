package com.example.weatherapp.data.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.model.CityModel
import io.reactivex.Observable

@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    fun getCities(): Observable<List<CityModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cityModels: List<CityModel>)

    @Query("DELETE FROM cities")
    fun deleteAll()
}