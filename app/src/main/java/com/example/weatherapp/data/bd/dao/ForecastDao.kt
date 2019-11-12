package com.example.weatherapp.data.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.entity.Forecast
import io.reactivex.Observable

@Dao
interface ForecastDao {

    @Query("SELECT * FROM forecast WHERE id = :cityId")
    fun getForecast(cityId: Long): Observable<Forecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: Forecast): Observable<Forecast>

    @Query("DELETE FROM forecast")
    fun deleteAll()
}