package com.example.weatherapp.data.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.entity.Weather
import io.reactivex.Observable

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE id = :cityId")
    fun getWeather(cityId: Long): Observable<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: Weather): Observable<Weather>

    @Query("DELETE FROM weather")
    fun deleteAll()
}