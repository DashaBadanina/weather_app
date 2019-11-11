package com.example.weatherapp.di

import com.example.weatherapp.presentation.cities.CitiesAdapter
import dagger.Module
import dagger.Provides

@Module
class CitiesActivityModule {

    @Provides
    @ActivityScope
    internal fun provideCitiesAdapter(): CitiesAdapter {
        return CitiesAdapter()
    }
}