package com.example.weatherapp.presentation.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.App
import com.example.weatherapp.di.ForecastActivityModule
import javax.inject.Inject

class ForecastActivity : AppCompatActivity() {

    private lateinit var model: ForecastViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(ForecastViewModel::class.java)
    }

    private fun inject() {
        val comp = (application as App).getComponent()
        DaggerForecastActivityComponent.builder()
            .citiesActivityModule(ForecastActivityModule())
            .appComponent(comp)
            .build()
            .inject(this)
    }
}