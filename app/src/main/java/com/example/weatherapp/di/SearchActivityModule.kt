package com.example.weatherapp.di

import com.example.weatherapp.presentation.search.SearchAdapter
import dagger.Module
import dagger.Provides

@Module
class SearchActivityModule {

    @Provides
    @ActivityScope
    internal fun provideSearchAdapter(): SearchAdapter {
        return SearchAdapter()
    }
}