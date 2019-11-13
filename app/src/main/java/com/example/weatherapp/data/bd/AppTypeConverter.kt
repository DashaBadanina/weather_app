package com.example.weatherapp.data.bd

import androidx.room.TypeConverter
import com.example.weatherapp.data.model.SimpleForecastData
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson



class AppTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun stringToSimpleForecastList(data: String): List<SimpleForecastData> {
        val listType = object : TypeToken<List<SimpleForecastData>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun simpleForecastListToString(someObjects: List<SimpleForecastData>): String {
        return gson.toJson(someObjects)
    }
}