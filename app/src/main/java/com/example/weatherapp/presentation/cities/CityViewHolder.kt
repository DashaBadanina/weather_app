package com.example.weatherapp.presentation.cities

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.PrepopulateDataProvider
import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.presentation.ImageLoader
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.city_view_holder.view.*

class CityViewHolder(
    itemView: View,
    itemClickListener: (Long) -> Unit,
    removeItemClickListener: (Long) -> Unit,
    private val itemWeatherData: (Long) -> Observable<WeatherModel>
) : RecyclerView.ViewHolder(itemView) {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private lateinit var currentCity: City

    init {
        itemView.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemClickListener(currentCity.id)
                }
            }
        }

        itemView.remove_city_bt.apply {
            setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    removeItemClickListener(currentCity.id)
                }
            }
        }
    }

    fun update(city: City) {
        this.currentCity = city
        disposable.add(
            itemWeatherData(currentCity.id)
                .subscribe { weather ->
                itemView.city_degree.text = weather.current_weather_temp
                ImageLoader.load(weather.current_weather_icon, itemView.city_weather_icon)
                })
        itemView.apply {
            if (PrepopulateDataProvider.provideCity(city.id)) {
                itemView.remove_city_bt.visibility = View.GONE
            } else {
                itemView.remove_city_bt.visibility = View.VISIBLE
            }
            this.city_name.text =
                resources.getString(R.string.city_and_country, city.name, city.country)
        }
    }
}