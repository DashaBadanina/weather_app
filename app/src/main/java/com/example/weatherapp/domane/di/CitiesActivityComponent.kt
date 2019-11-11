package com.example.weatherapp.domane.di

import com.example.weatherapp.presentation.cities.CitiesActivity
import com.example.weatherapp.presentation.cities.CitiesAdapter
import dagger.Component

@Component(modules = [CitiesActivityModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface CitiesActivityComponent {

    fun getCitiesAdapter(): CitiesAdapter
    fun inject(citiesActivity: CitiesActivity)
}