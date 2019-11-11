package com.example.weatherapp.presentation.forecast

import androidx.lifecycle.ViewModel
import com.example.weatherapp.domane.usecase.WeatherInteractor
import javax.inject.Inject

class ForecastViewModel
@Inject constructor(val weatherInteractor: WeatherInteractor) : ViewModel() {
}