package com.example.weatherapp.presentation.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weatherapp.App
import com.example.weatherapp.R
import com.example.weatherapp.data.entity.SearchCity
import com.example.weatherapp.data.entity.SearchCityResult
import com.example.weatherapp.di.DaggerSearchActivityComponent
import com.example.weatherapp.di.SearchActivityModule
import kotlinx.android.synthetic.main.search_activity.*
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
        initUi()
        model = ViewModelProviders.of(this, viewModelFactory)
            .get(SearchViewModel::class.java)

        model.getError().observe(this, Observer<Any> { error ->
            error?.let {
                onError()
            }
        })

        model.getSearchResult().observe(this, Observer<List<SearchCity>> { list ->
            list?.let {
                    adapter.data = it
            }
        })
    }

    override fun onResume() {
        super.onResume()
    }

    private fun onModelUpdate() {

    }

    private fun onError() {
        no_result_message.visibility = View.VISIBLE
    }

    private fun doSearch(cityName: String) {
        model.loadSearchResult(cityName)
    }

    private fun addCity(searchCity: SearchCity) {
        model.addCity(searchCity)
    }

    private fun initUi() {
        search_list.adapter = adapter
        adapter.itemClickListener = ::addCity
        search_field.setOnEditorActionListener { _: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(search_field.text.toString())
                true
            } else {
                false
            }
        }
        search_field.setOnKeyListener { _: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch(search_field.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun inject() {
        val comp = (application as App).getComponent()
        DaggerSearchActivityComponent.builder()
            .searchActivityModule(SearchActivityModule())
            .appComponent(comp)
            .build()
            .inject(this)
    }
}