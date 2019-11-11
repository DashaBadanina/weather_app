package com.example.weatherapp.presentation.cities

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.entity.City
import kotlinx.android.synthetic.main.city_view_holder.view.*

class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun update (city: City, ) {
        itemView.apply {
            this.city_name.text = city.name
        }
    }
}