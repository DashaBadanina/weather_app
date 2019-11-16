package com.example.weatherapp.data

import com.example.weatherapp.data.model.City

class PrepopulateDataProvider {

    val cities = arrayListOf(
        City(511196, "Perm", "RU"),
        City(524901, "Moscow", "RU")
    )

    fun getPrePopulatedCities(): List<City> {
        return cities
    }
}