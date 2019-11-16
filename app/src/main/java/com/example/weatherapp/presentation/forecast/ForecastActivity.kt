package com.example.weatherapp.presentation.forecast

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.App
import com.example.weatherapp.R
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.di.DaggerForecastActivityComponent
import com.example.weatherapp.di.ForecastActivityModule
import com.example.weatherapp.presentation.ImageLoader
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

        model.getError().observe(this, Observer<Any> {
            onError()
        })
        model.getForecast().observe(this, Observer<ForecastModel> { forecast ->
            onDataUpdate(forecast)
        })
    }

    override fun onResume() {
        super.onResume()
        updateForecast()
    }

    private fun updateForecast() {
        model.loadForecast(
            intent.getLongExtra(InitialData.CITY_ID, 0)
        )
    }

    private fun onDataUpdate(forecast: ForecastModel?) {
        forecast?.let {
            city_name.text =
                resources.getString(R.string.city_and_country, forecast.name, forecast.country)
            city_description.text = forecast.current_weather_desc
            city_degree.text =
                resources.getString(R.string.degree_celsius, forecast.current_weather_temp)
            ImageLoader.load(forecast.current_weather_icon, city_weather_icon)
            city_forecast.visibility = View.VISIBLE
            adapter.data = it.forecast
        }
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show()
    }

    private fun initUi() {
        forecast_list.adapter = adapter
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