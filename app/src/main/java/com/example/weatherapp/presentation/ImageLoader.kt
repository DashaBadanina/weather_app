package com.example.weatherapp.presentation

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weatherapp.App

object ImageLoader {

    private val FORMATTED_IMAGE_URL = "http://openweathermap.org/img/wn/%s@2x.png"

    private fun formattedImageUrl(url: String) = String.format(FORMATTED_IMAGE_URL, url)

    fun load(
        urlPart: String,
        view: ImageView
    ) {
        Glide
            .with(App.instance())
            .load(formattedImageUrl(urlPart))
            .into(view)
    }
}