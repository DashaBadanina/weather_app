package com.example.weatherapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "dt")
    val dt: Long,
    @ColumnInfo(name = "weather")
    val weather: List<WeatherCondition>,
    @ColumnInfo(name = "main")
    val main: WeatherMain
)