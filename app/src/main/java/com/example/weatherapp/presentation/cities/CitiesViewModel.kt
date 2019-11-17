package com.example.weatherapp.presentation.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.domane.usecase.CityInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CitiesViewModel
@Inject constructor(val cityInteractor: CityInteractor) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val cities: MutableLiveData<List<City>> = MutableLiveData()
    private val error: MutableLiveData<Any> = MutableLiveData()

    fun getCities(): LiveData<List<City>> = cities

    fun getError(): LiveData<Any> = error

    fun loadCities() {
        disposable.add(
            cityInteractor
                .getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cities.postValue(it) }, { error.postValue(Any()) })
        )
    }

    fun removeCity(cityId: Long) {
        disposable.add(
            cityInteractor
                .deleteCity(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cities.postValue(it) }, { error.postValue(Any()) })
        )
    }

    fun loadCurrentWeather(cityId: Long): Observable<WeatherModel> {
        return cityInteractor
            .getCurrentWeather(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}