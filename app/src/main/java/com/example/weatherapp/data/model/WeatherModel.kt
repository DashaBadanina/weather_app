package com.example.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherModel (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long
)