package com.example.weatherapp.data.entity

data class WeatherMain(
    val temp: Float,
    val pressure: Int,
    val humidity: Int,
    val temp_min: Float,
    val temp_max: Float,
    val sea_level: Int
)