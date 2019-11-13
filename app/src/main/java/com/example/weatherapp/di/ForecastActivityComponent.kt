package com.example.weatherapp.di

import com.example.weatherapp.presentation.forecast.ForecastActivity
import com.example.weatherapp.presentation.forecast.ForecastAdapter
import dagger.Component

@Component(modules = [ForecastActivityModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface ForecastActivityComponent {

    fun getForecastAdapter(): ForecastAdapter
    fun inject(forecastActivity: ForecastActivity)
}