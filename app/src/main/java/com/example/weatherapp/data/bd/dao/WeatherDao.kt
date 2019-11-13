package com.example.weatherapp.data.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.model.WeatherModel
import io.reactivex.Observable

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE id = :cityId")
    fun getWeather(cityId: Long): Observable<WeatherModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: WeatherModel)

    @Query("DELETE FROM weather")
    fun deleteAll()
}