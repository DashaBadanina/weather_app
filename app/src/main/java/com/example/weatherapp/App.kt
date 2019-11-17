package com.example.weatherapp

import android.app.Application
import android.content.Context
import com.example.weatherapp.data.PrepopulateDataProvider
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.AppModule
import com.example.weatherapp.di.DaggerAppComponent
import io.reactivex.schedulers.Schedulers

class App : Application() {


    private lateinit var appComponent: AppComponent

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
            .saveAll(PrepopulateDataProvider.getPrePopulatedCities())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    companion object {
        lateinit var instance: App

        fun instance() : Context {
            return instance
        }
    }
}