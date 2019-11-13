package com.example.weatherapp.data.model.mapper

import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.entity.WeatherData
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.model.SimpleForecastData
import com.example.weatherapp.domane.DateUtils
import kotlin.math.round

class ForecastMapper : Mapper<Pair<Forecast, Weather>, ForecastModel>() {

    override fun mapImpl(item: Pair<Forecast, Weather>): ForecastModel {
        return map(item.first, item.second)
    }

    private fun map(forecast: Forecast, weather: Weather): ForecastModel {
        return ForecastModel(
            id = forecast.cityModel.id,
            name = forecast.cityModel.name,
            country = forecast.cityModel.country,
            current_weather_desc = weather.weather[0].description,
            current_weather_temp = round(weather.main.temp).toString(),
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
                    temp = round(item.main.temp).toString(),
                    icon = item.weather[0].icon
                )
            }
    }
}