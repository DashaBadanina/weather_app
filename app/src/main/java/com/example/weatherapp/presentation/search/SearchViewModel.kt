package com.example.weatherapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.entity.SearchCityResult
import com.example.weatherapp.domane.usecase.SearchInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel
@Inject constructor(val searchInteractor: SearchInteractor) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val searchCityResult: MutableLiveData<SearchCityResult> = MutableLiveData()
    private val state: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<Boolean> = MutableLiveData()

    fun getSearchResult(): LiveData<SearchCityResult> = searchCityResult

    fun getState(): LiveData<Boolean> = state

    fun getError(): LiveData<Boolean> = error

    fun loadSearchResult(cityName: String) {
        disposable.add(
            searchInteractor
                .searchCitis(cityName)
                .doOnSubscribe {
                    state.postValue(true)
                }
                .map { it }
                .doOnNext { state.postValue(false) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchCityResult.postValue(it) }, { error.postValue(true) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}