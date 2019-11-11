package com.example.weatherapp.di

import com.example.weatherapp.presentation.forecast.ForecastActivity
import dagger.Component

@Component(modules = [ForecastActivityModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface ForecastActivityComponent {

    fun inject(forecastActivity: ForecastActivity)
}