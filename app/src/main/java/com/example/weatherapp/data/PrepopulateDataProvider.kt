package com.example.weatherapp.data

import com.example.weatherapp.data.model.City

object PrepopulateDataProvider {

    val cities = arrayListOf(
        City(511196, "Perm", "RU"),
        City(524901, "Moscow", "RU")
    )

    fun getPrePopulatedCities(): List<City> {
        return cities
    }

    fun provideCity(id: Long): Boolean {
        return cities.any { city -> city.id == id }
    }
}