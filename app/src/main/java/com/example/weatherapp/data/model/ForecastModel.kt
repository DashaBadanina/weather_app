package com.example.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.data.bd.AppTypeConverter

@Entity(tableName = "forecast")
data class ForecastModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "description")
    val current_weather_desc: String,
    @ColumnInfo(name = "temp")
    val current_weather_temp: String,
    @ColumnInfo(name = "icon")
    val current_weather_icon: String,
    @ColumnInfo(name = "forecast")
    @TypeConverters(AppTypeConverter::class)
    val forecast: List<SimpleForecastData>
)