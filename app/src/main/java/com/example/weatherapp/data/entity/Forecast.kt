package com.example.weatherapp.data.entity

import com.example.weatherapp.data.model.City

data class Forecast (
    val list: List<WeatherData>,
    val city: City
)