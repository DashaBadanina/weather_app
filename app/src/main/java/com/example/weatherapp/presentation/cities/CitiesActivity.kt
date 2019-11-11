package com.example.weatherapp.presentation.cities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.App
import com.example.weatherapp.data.entity.City
import com.example.weatherapp.domane.di.CitiesActivityModule
import com.example.weatherapp.domane.di.DaggerCitiesActivityComponent
import com.example.weatherapp.presentation.viewmodel.CustomViewModelFactory
import javax.inject.Inject

class CitiesActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: CitiesAdapter
    private lateinit var model: CitiesViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(CitiesViewModel::class.java)

        model.getCities().observe(this, Observer<List<City>> {cities ->
            cities?.let {
                adapter.data = it
            }
        })
    }

    fun inject() {
        val comp = (application as App).getComponent()
        DaggerCitiesActivityComponent.builder()
            .citiesActivityModule(CitiesActivityModule())
            .appComponent(comp)
            .build()
            .inject(this)
    }
}