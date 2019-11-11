package com.example.weatherapp.data.entity

data class WeatherData(
    val weather: List<WeatherCondition>,
    val main: WeatherMain,
    val dt_txt: String
)