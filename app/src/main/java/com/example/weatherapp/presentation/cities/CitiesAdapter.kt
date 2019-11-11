package com.example.weatherapp.presentation.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.City

class CitiesAdapter : RecyclerView.Adapter<CityViewHolder>() {

    var data: List<City> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_view_holder, parent, false)
        return CityViewHolder(viewItem)

    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.update(data[position])
    }
}