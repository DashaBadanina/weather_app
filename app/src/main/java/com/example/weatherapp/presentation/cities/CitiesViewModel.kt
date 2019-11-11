package com.example.weatherapp.presentation.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.entity.City
import com.example.weatherapp.domane.usecase.CityInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CitiesViewModel
@Inject constructor(val cityInteractor: CityInteractor) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val cities: MutableLiveData<List<City>> = MutableLiveData()
    private val state: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<Boolean> = MutableLiveData()

    fun getCities(): LiveData<List<City>> = cities

    fun getState(): LiveData<Boolean> = state

    fun getError(): LiveData<Boolean> = error

    fun loadCities() {
        disposable.add(
            cityInteractor
                .getCities()
                .doOnSubscribe {
                    state.postValue(true)
                }
                .map { it }
                .doOnNext { state.postValue(false) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cities.postValue(it) }, { error.postValue(true) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}