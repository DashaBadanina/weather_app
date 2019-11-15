package com.example.weatherapp.presentation.cities

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.City
import kotlinx.android.synthetic.main.city_view_holder.view.*

class CityViewHolder(
    itemView: View,
    itemClickListener: (Long) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private lateinit var currentCity: City

    init {
        itemView.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemClickListener(currentCity.id)
                }
            }
        }

    }

    fun update(city: City) {
        this.currentCity = city
        itemView.apply {
            this.city_name.text = resources.getString(R.string.city_and_country, city.name, city.country)
        }
    }
}