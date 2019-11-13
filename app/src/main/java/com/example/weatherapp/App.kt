package com.example.weatherapp

import android.app.Application
import android.content.Context
import com.example.weatherapp.data.model.City
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.AppModule
import com.example.weatherapp.di.DaggerAppComponent
import io.reactivex.schedulers.Schedulers

class App : Application() {


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createAppComponent()
        initPrePopulateDb()
    }

    fun getComponent(): AppComponent? {
        return appComponent
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    private fun initPrePopulateDb() {
        appComponent
            .getCityRepository()
            .saveAll(getPrePopulatedCities())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun getPrePopulatedCities(): List<City> {
        return arrayListOf(
            City(511196, "Perm", "RU"),
            City(524901, "Moscow", "RU")
        )
    }

    companion object {
        lateinit var instance: App

        fun instance() : Context {
            return instance
        }
    }
}