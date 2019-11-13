package com.example.weatherapp.presentation.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.weatherapp.App
import com.example.weatherapp.R
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.data.network.NetworkConfig
import com.example.weatherapp.di.DaggerForecastActivityComponent
import com.example.weatherapp.di.ForecastActivityModule
import kotlinx.android.synthetic.main.forecast_activity.*
import javax.inject.Inject

class ForecastActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: ForecastAdapter
    private lateinit var model: ForecastViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_activity)
        inject()
        initUi()
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(ForecastViewModel::class.java)

        model.getForecast().observe(this, Observer<ForecastModel> { forecast ->
            city_name.text = forecast.name
            city_description.text = forecast.current_weather_desc
            city_degree.text = resources.getString(R.string.degree_celsius, forecast.current_weather_temp)
            Glide
                .with(city_weather_icon)
                .load(NetworkConfig.formattedImageUrl(forecast.current_weather_icon))
                .into(city_weather_icon)
            forecast?.let {
                adapter.data = it.forecast
            }
        })
    }

    override fun onResume() {
        super.onResume()
        updateForecast()
    }

    private fun initUi() {
        forecast_list.adapter = adapter
    }

    private fun updateForecast() {
        model.loadForecast(
            intent.getLongExtra(InitialData.CITY_ID, 0)
        )
    }

    private fun inject() {
        val comp = (application as App).getComponent()
        DaggerForecastActivityComponent.builder()
            .forecastActivityModule(ForecastActivityModule())
            .appComponent(comp)
            .build()
            .inject(this)
    }

    object InitialData {
        val CITY_ID = "CITY_ID"
    }
}