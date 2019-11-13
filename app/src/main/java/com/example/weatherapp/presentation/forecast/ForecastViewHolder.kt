package com.example.weatherapp.presentation.forecast

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.model.SimpleForecastData
import com.example.weatherapp.data.network.NetworkConfig
import kotlinx.android.synthetic.main.forecast_view_holder.view.*

class ForecastViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun update(forecastData: SimpleForecastData) {
        itemView.apply {
            this.forecast_date.text = forecastData.date
            this.forecast_degree.text = resources.getString(R.string.degree_celsius, forecastData.temp)
            Glide
                .with(this)
                .load(NetworkConfig.formattedImageUrl(forecastData.icon))
                .into(this.forecast_icon)
        }
    }
}