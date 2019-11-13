package com.example.weatherapp.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.data.bd.dao.CityDao
import com.example.weatherapp.data.bd.dao.ForecastDao
import com.example.weatherapp.data.bd.dao.WeatherDao
import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.model.WeatherModel

@Database(
    entities = [
        City::class,
        ForecastModel::class,
        WeatherModel::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(AppTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun forecastDao(): ForecastDao
    abstract fun weatherDao(): WeatherDao
}