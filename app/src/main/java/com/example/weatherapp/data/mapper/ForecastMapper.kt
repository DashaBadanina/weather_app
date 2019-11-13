package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.entity.WeatherData
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.model.SimpleForecastData
import com.example.weatherapp.domane.DateUtils
import kotlin.math.roundToInt

class ForecastMapper : Mapper<Pair<Forecast, Weather>, ForecastModel>() {

    override fun mapImpl(item: Pair<Forecast, Weather>): ForecastModel {
        return map(item.first, item.second)
    }

    private fun map(forecast: Forecast, weather: Weather): ForecastModel {
        return ForecastModel(
            id = forecast.city.id,
            name = forecast.city.name,
            country = forecast.city.country,
            current_weather_desc = weather.weather[0].description,
            current_weather_temp = weather.main.temp.roundToInt().toString(),
            current_weather_icon = weather.weather[0].icon,
            forecast = getSimpleForecastDataList(forecast.list)
        )
    }

    private fun getSimpleForecastDataList(list: List<WeatherData>): List<SimpleForecastData> {
        return list
            .map {item ->
                SimpleForecastData(
                    day = DateUtils.getFormattedDay(item.dt),
                    time = DateUtils.getFormattedTime(item.dt),
                    temp = item.main.temp.roundToInt().toString(),
                    icon = item.weather[0].icon
                )
            }
    }
}