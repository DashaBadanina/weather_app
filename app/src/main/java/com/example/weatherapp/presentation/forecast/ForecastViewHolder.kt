package com.example.weatherapp.presentation.forecast

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.SimpleForecastData
import com.example.weatherapp.presentation.ImageLoader
import kotlinx.android.synthetic.main.forecast_view_holder.view.*

class ForecastViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun update(forecastData: SimpleForecastData) {
        itemView.apply {
            this.forecast_day.text = forecastData.day
            this.forecast_time.text = forecastData.time
            this.forecast_degree.text = resources.getString(R.string.degree_celsius, forecastData.temp)
            ImageLoader.load(forecastData.icon, this.forecast_icon)
        }
    }
}