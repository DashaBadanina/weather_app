package com.example.weatherapp.presentation.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.App
import com.example.weatherapp.R
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: SearchAdapter
    private lateinit var model: SearchViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        inject()
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(SearchViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun inject() {
        val comp = (application as App).getComponent()
        DaggerSearchActivityComponent.builder()
            .forecastActivityModule(SearchActivity())
            .appComponent(comp)
            .build()
            .inject(this)
    }
}