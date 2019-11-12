package com.example.weatherapp.domane.model

abstract class Mapper<FromT, ToT> {

    abstract fun mapImpl(item: FromT): ToT
}