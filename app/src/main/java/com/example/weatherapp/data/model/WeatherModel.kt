package com.example.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherModel (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "temp")
    val current_weather_temp: String,
    @ColumnInfo(name = "icon")
    val current_weather_icon: String
)