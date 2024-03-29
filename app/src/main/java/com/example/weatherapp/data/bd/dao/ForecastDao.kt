package com.example.weatherapp.data.bd.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.model.ForecastModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface ForecastDao {

    @Query("SELECT * FROM forecast WHERE id = :cityId")
    fun getForecast(cityId: Long): Single<ForecastModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: ForecastModel)

    @Query("DELETE FROM forecast WHERE id = :cityId")
    fun delete(cityId: Long)
}