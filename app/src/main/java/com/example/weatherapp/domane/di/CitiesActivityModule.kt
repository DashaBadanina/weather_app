package com.example.weatherapp.domane.di

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