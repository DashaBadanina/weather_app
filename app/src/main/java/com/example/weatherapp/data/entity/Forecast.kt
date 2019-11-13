package com.example.weatherapp.data.entity

import com.example.weatherapp.data.model.CityModel

data class Forecast (
    val list: List<WeatherData>,
    val cityModel: CityModel
)