package com.example.weatherapp.data.mapper

abstract class Mapper<FromT, ToT> {

    abstract fun mapImpl(item: FromT): ToT
}