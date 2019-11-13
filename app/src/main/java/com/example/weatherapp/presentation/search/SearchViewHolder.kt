package com.example.weatherapp.presentation.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.SearchCity
import com.example.weatherapp.presentation.ImageLoader
import kotlinx.android.synthetic.main.search_view_holder.view.*
import kotlin.math.round
import kotlin.math.roundToInt

class SearchViewHolder(
    itemView: View,
    itemClickListener: (SearchCity) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private lateinit var currentSearchCity: SearchCity

    init {
        itemView.add_city_bt.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemClickListener(currentSearchCity)
                }
            }
        }

    }

    fun update(searchCity: SearchCity) {
        this.currentSearchCity = searchCity
        itemView.apply {
            this.search_city_name.text = resources.getString(
                R.string.city_and_country,
                searchCity.name,
                searchCity.sys.country
            )
            this.search_city_weather_degree.text = resources.getString(R.string.degree_celsius, searchCity.main.temp.roundToInt().toString())
            ImageLoader.load(searchCity.weather[0].icon, this.search_city_weather_icon)
        }
    }
}