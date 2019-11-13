package com.example.weatherapp.presentation.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.SimpleForecastData

class ForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {

    var data: List<SimpleForecastData> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_view_holder, parent, false)
        return ForecastViewHolder(viewItem)

    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.update(data[position])
    }
}