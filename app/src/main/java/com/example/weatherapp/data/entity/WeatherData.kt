package com.example.weatherapp.data.entity

data class WeatherData(
    val dt: Long,
    val weather: List<WeatherCondition>,
    val main: WeatherMain
)