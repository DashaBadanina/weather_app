package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.entity.SearchCity
import com.example.weatherapp.data.model.City

class CityMapper : Mapper<SearchCity, City>() {

    override fun mapImpl(item: SearchCity): City {
        return City(
            id = item.id,
            name = item.name,
            country = item.sys.country
        )
    }
}