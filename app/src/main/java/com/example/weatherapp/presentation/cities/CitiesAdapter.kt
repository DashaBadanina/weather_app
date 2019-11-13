package com.example.weatherapp.presentation.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.model.CityModel

class CitiesAdapter : RecyclerView.Adapter<CityViewHolder>() {

    var data: List<CityModel> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    lateinit var itemClickListener: (Long) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_view_holder, parent, false)
        return CityViewHolder(viewItem, itemClickListener)

    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.update(data[position])
    }
}