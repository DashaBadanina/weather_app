package com.example.weatherapp.data.network

object NetworkConfig {

    val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    val APPID = "a0c6074aedcb97ba4801f6cd5e0df334"
    val TEMP_UNITS = "metric"
    private val FORMATTED_IMAGE_URL = "http://openweathermap.org/img/wn/%s@2x.png"

    fun formattedImageUrl(url : String) = String.format(FORMATTED_IMAGE_URL, url)
}