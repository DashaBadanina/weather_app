package com.example.weatherapp.di

import com.example.weatherapp.presentation.search.SearchActivity
import com.example.weatherapp.presentation.search.SearchAdapter
import dagger.Component

@Component(modules = [SearchActivityComponent::class], dependencies = [AppComponent::class])
@ActivityScope
interface SearchActivityComponent {

    fun getSearchAdapter(): SearchAdapter
    fun inject(searchActivity: SearchActivity)
}