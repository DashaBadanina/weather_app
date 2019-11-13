package com.example.weatherapp.domane.usecase

import com.example.weatherapp.data.model.ForecastModel
import com.example.weatherapp.domane.repository.ForecastRepository
import io.reactivex.Observable
import javax.inject.Inject

class ForecastInteractor @Inject constructor(
    private val forecastRepository: ForecastRepository
) {

    fun getForecast(cityId: Long) : Observable<ForecastModel> {
        return forecastRepository
            .get(cityId)
    }
}