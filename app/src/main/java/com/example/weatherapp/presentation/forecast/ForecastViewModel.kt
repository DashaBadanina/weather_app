package com.example.weatherapp.presentation.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.entity.Forecast
import com.example.weatherapp.domane.usecase.ForecastInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ForecastViewModel
@Inject constructor(val forecastInteractor: ForecastInteractor) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val forecast: MutableLiveData<Forecast> = MutableLiveData()
    private val state: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<Boolean> = MutableLiveData()

    fun getForecast(): LiveData<Forecast> = forecast

    fun getState(): LiveData<Boolean> = state

    fun getError(): LiveData<Boolean> = error

    fun loadForecast(cityId: Long) {
        disposable.add(
            forecastInteractor
                .getForecast(cityId)
                .doOnSubscribe {
                    state.postValue(true)
                }
                .map { it }
                .doOnNext { state.postValue(false) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecast.postValue(it) }, { error.postValue(true) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}