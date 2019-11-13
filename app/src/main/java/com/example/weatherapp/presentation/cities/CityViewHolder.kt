package com.example.weatherapp.presentation.cities

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.model.CityModel
import kotlinx.android.synthetic.main.city_view_holder.view.*

class CityViewHolder(
    itemView: View,
    itemClickListener: (Long) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private lateinit var currentCityModel: CityModel

    init {
        itemView.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemClickListener(currentCityModel.id)
                }
            }
        }

    }

    fun update(cityModel: CityModel) {
        this.currentCityModel = cityModel
        itemView.apply {
            this.city_name.text = cityModel.name
        }
    }
}