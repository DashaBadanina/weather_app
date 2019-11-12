package com.example.weatherapp.presentation.cities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.App
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.City
import com.example.weatherapp.di.CitiesActivityModule
import com.example.weatherapp.di.DaggerCitiesActivityComponent
import com.example.weatherapp.presentation.forecast.ForecastActivity
import kotlinx.android.synthetic.main.current_weather_activity.*
import javax.inject.Inject

class CitiesActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: CitiesAdapter
    private lateinit var model: CitiesViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.current_weather_activity)
        inject()
        initUi()
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(CitiesViewModel::class.java)

        model.getCities().observe(this, Observer<List<City>> { cities ->
            cities?.let {
                adapter.data = it
            }
        })
    }

    override fun onResume() {
        super.onResume()
        updateCities()
    }

    private fun toForecastActivity(citeId: Long) {
        val i = Intent(this, ForecastActivity::class.java)
        i.putExtra(ForecastActivity.InitialData.CITY_ID, citeId)
        startActivity(i)
    }

    private fun initUi() {
        cities_rv.adapter = adapter
        adapter.itemClickListener = ::toForecastActivity
    }

    private fun updateCities() {
        model.loadCities()
    }

    private fun inject() {
        val comp = (application as App).getComponent()
        DaggerCitiesActivityComponent.builder()
            .citiesActivityModule(CitiesActivityModule())
            .appComponent(comp)
            .build()
            .inject(this)
    }
}