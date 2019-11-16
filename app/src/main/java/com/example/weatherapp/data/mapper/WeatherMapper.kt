package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.model.WeatherModel
import kotlin.math.roundToInt

class WeatherMapper : Mapper<Weather, WeatherModel>() {

    override fun mapImpl(item: Weather): WeatherModel {
        return WeatherModel(
            id = item.id,
            current_weather_temp = item.main.temp.roundToInt().toString(),
            current_weather_icon = item.weather[0].icon
        )
    }

}