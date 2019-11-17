package com.example.weatherapp.presentation.cities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.App
import com.example.weatherapp.R
import com.example.weatherapp.data.model.City
import com.example.weatherapp.di.CitiesActivityModule
import com.example.weatherapp.di.DaggerCitiesActivityComponent
import com.example.weatherapp.presentation.forecast.ForecastActivity
import com.example.weatherapp.presentation.search.SearchActivity
import kotlinx.android.synthetic.main.city_activity.*
import javax.inject.Inject

class CitiesActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: CitiesAdapter
    private lateinit var model: CitiesViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_activity)
        inject()
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(CitiesViewModel::class.java)
        initUi()
        model.getError().observe(this,Observer<Any>{
            onError()
        } )
        model.getCities().observe(this, Observer<List<City>> { cities ->
            onDataUpdate(cities)
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

    private fun toSearchActivity() {
        val i = Intent(this, SearchActivity::class.java)
        startActivity(i)
    }

    private fun updateCities() {
        model.loadCities()
    }

    private fun removeCity(cityId: Long) {
        model.removeCity(cityId)
    }

    private fun onDataUpdate(cities: List<City>?) {
        cities?.let {
            adapter.data = it
        }
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show()
    }

    private fun initUi() {
        search_view.setOnClickListener { toSearchActivity() }
        cities_list.adapter = adapter
        adapter.itemClickListener = ::toForecastActivity
        adapter.itemWeatherData = model::loadCurrentWeather
        adapter.removeItemClickListener = ::removeCity
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