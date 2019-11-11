package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.domane.di.AppComponent
import com.example.weatherapp.domane.di.AppModule
import com.example.weatherapp.domane.di.DaggerAppComponent

class App : Application() {


    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getComponent(): AppComponent? {
        return appComponent
    }
}