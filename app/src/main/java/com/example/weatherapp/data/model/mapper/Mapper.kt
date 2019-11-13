package com.example.weatherapp.data.model.mapper

abstract class Mapper<FromT, ToT> {

    abstract fun mapImpl(item: FromT): ToT
}