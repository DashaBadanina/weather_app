package com.example.weatherapp.presentation.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.App
import com.example.weatherapp.R
import com.example.weatherapp.di.DaggerForecastActivityComponent
import com.example.weatherapp.di.ForecastActivityModule
import javax.inject.Inject

class ForecastActivity : AppCompatActivity() {

    private lateinit var model: ForecastViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_activity)
        inject()
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(ForecastViewModel::class.java)
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