package com.example.weatherapp.data.entity

data class Weather (
    val id: Long,
    val weather: List<WeatherCondition>,
    val main: WeatherMain
)