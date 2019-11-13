package com.example.weatherapp.data.entity

data class SearchCity(
    val id: Long,
    val name: String,
    val sys: Country,
    val weather: List<WeatherCondition>,
    val main: WeatherMain
)