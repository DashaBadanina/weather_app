package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.bd.AppDatabase
import com.example.weatherapp.data.bd.dao.CityDao
import com.example.weatherapp.data.bd.dao.ForecastDao
import com.example.weatherapp.data.bd.dao.WeatherDao
import com.example.weatherapp.data.mapper.CityMapper
import com.example.weatherapp.data.mapper.ForecastMapper
import com.example.weatherapp.data.network.NetworkConfig
import com.example.weatherapp.data.network.OpenWeatherApi
import com.example.weatherapp.data.repository.CityRepositoryImpl
import com.example.weatherapp.data.repository.ForecastRepositoryImpl
import com.example.weatherapp.data.repository.SearchRepositoryImpl
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domane.repository.CityRepository
import com.example.weatherapp.domane.repository.ForecastRepository
import com.example.weatherapp.domane.repository.SearchRepository
import com.example.weatherapp.domane.repository.WeatherRepository
import com.example.weatherapp.domane.usecase.CityInteractor
import com.example.weatherapp.domane.usecase.ForecastInteractor
import com.example.weatherapp.domane.usecase.SearchInteractor
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
    fun provideForecastMapper(): ForecastMapper {
        return ForecastMapper()
    }

    @Singleton
    @Provides
    fun provideCityMapper(): CityMapper {
        return CityMapper()
    }

    @Singleton
    @Provides
    fun provideCityDao(db: AppDatabase): CityDao {
        return db.cityDao()
    }

    @Singleton
    @Provides
    fun provideForecastDao(db: AppDatabase): ForecastDao {
        return db.forecastDao()
    }

    @Singleton
    @Provides
    fun provideWeatherDao(db: AppDatabase): WeatherDao {
        return db.weatherDao()
    }

    @Singleton
    @Provides
    fun provideCityReposytory(cityDao: CityDao): CityRepository {
        return CityRepositoryImpl(cityDao)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(api: OpenWeatherApi, weatherDao: WeatherDao): WeatherRepository {
        return WeatherRepositoryImpl(api, weatherDao)
    }

    @Singleton
    @Provides
    fun provideForecastRepository(
        api: OpenWeatherApi,
        forecastDao: ForecastDao,
        forecastMapper: ForecastMapper
    ): ForecastRepository {
        return ForecastRepositoryImpl(api, forecastDao, forecastMapper)
    }

    @Singleton
    @Provides
    fun provideSearchRepositoryRepository(api: OpenWeatherApi): SearchRepository {
        return SearchRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideCityInteractor(
        cityRepository: CityRepository,
        weatherRepository: WeatherRepository
    ): CityInteractor {
        return CityInteractor(cityRepository, weatherRepository)
    }

    @Singleton
    @Provides
    fun provideForecastInteractor(
        forecastRepository: ForecastRepository
    ): ForecastInteractor {
        return ForecastInteractor(forecastRepository)
    }

    @Singleton
    @Provides
    fun provideSearchInteractor(
        searchRepository: SearchRepository,
        cityRepository: CityRepository,
        cityMapper: CityMapper
    ): SearchInteractor {
        return SearchInteractor(searchRepository, cityRepository, cityMapper)
    }
}