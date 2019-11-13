package com.example.weatherapp.presentation.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.entity.SearchCity
import com.example.weatherapp.presentation.ImageLoader
import kotlinx.android.synthetic.main.search_view_holder.view.*
import kotlin.math.round

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun update(searchCity: SearchCity) {
        itemView.apply {
            this.search_city_name.text = searchCity.name
            this.search_city_weather_degree.text =
                round(searchCity.weatherData.main.temp).toString()
            ImageLoader.load(searchCity.weatherData.weather[0].icon, this.search_city_weather_icon)
        }
    }
}