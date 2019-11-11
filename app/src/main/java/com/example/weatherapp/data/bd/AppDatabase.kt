package com.example.weatherapp.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.bd.dao.CityDao
import com.example.weatherapp.data.entity.City

@Database(
    entities = [City::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cityDao(): CityDao
}