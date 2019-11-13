package com.example.weatherapp.data.model.mapper

import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.data.entity.Weather
import com.example.weatherapp.data.entity.WeatherData
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.model.SimpleForecastData

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
            current_weather_temp = weather.main.temp.toString(),
            current_weather_icon = weather.weather[0].icon,
            forecast = getSimpleForecastDataList(forecast.list)
        )
    }

    private fun getSimpleForecastDataList(list: List<WeatherData>) : List<SimpleForecastData> {
        return arrayListOf(
            SimpleForecastData(
                date = list[0].dt.toString(),
                temp = list[0].main.temp.toString(),
                icon = list[0].weather[0].icon
            )
        )
    }
}