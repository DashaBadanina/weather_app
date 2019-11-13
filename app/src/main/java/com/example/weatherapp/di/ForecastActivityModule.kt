package com.example.weatherapp.di

import com.example.weatherapp.presentation.forecast.ForecastAdapter
import dagger.Module
import dagger.Provides

@Module
class ForecastActivityModule {

    @Provides
    @ActivityScope
    internal fun provideCitiesAdapter(): ForecastAdapter {
        return ForecastAdapter()
    }
}