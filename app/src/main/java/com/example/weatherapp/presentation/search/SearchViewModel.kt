package com.example.weatherapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.entity.SearchCity
import com.example.weatherapp.data.entity.SearchCityResult
import com.example.weatherapp.domane.usecase.SearchInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel
@Inject constructor(val searchInteractor: SearchInteractor) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val searchCityResult: MutableLiveData<List<SearchCity>> = MutableLiveData()
    private val error: MutableLiveData<Any> = MutableLiveData()

    fun getSearchResult(): LiveData<List<SearchCity>> = searchCityResult

    fun getError(): LiveData<Any> = error

    fun loadSearchResult(cityName: String) {
        disposable.add(
            searchInteractor
                .searchCities(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchCityResult.postValue(it) }, { error.postValue(Any()) })
        )
    }

    fun addCity(searchCity: SearchCity, cityName: String) {
        disposable.add(
            searchInteractor
                .saveCity(searchCity, cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({searchCityResult.postValue(it) },{ error.postValue(Any()) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}