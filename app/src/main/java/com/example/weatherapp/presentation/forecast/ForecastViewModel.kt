package com.example.weatherapp.presentation.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.domane.usecase.ForecastInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ForecastViewModel
@Inject constructor(val forecastInteractor: ForecastInteractor) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val forecast: MutableLiveData<ForecastModel> = MutableLiveData()
    private val state: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<Any> = MutableLiveData()

    fun getForecast(): LiveData<ForecastModel> = forecast

    fun getError(): LiveData<Any> = error

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
                .subscribe({ forecast.postValue(it) }, { error.postValue(Any()) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}