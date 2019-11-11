package com.example.weatherapp.domane.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.bd.AppDatabase
import com.example.weatherapp.data.bd.dao.CityDao
import com.example.weatherapp.data.entity.City
import com.example.weatherapp.data.network.NetworkConfig
import com.example.weatherapp.data.network.OpenWeatherApi
import com.example.weatherapp.data.repository.CityRepositoryImpl
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domane.repository.CityRepository
import com.example.weatherapp.domane.repository.WeatherRepository
import com.example.weatherapp.domane.usecase.CityInteractor
import com.example.weatherapp.domane.usecase.WeatherInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    private val context: Context

    constructor(context: Context) {
        this.context = context
    }

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkConfig.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): OpenWeatherApi {
        return retrofit.create(OpenWeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "AppDb.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideCityDao(db: AppDatabase): CityDao {
        db.cityDao().insertCities(
            arrayListOf(
                City(511196, "Perm", "RU"),
                City(524901, "Moscow", "RU"))
        )
        return db.cityDao()
    }

    @Singleton
    @Provides
    fun provideCityReposytory(cityDao: CityDao): CityRepository {
        return CityRepositoryImpl(cityDao)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(api: OpenWeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideCityInteractor(repository: CityRepository): CityInteractor {
        return CityInteractor(repository)
    }

    @Singleton
    @Provides
    fun provideWeatherInteractor(repository: WeatherRepository): WeatherInteractor {
        return WeatherInteractor(repository)
    }
}