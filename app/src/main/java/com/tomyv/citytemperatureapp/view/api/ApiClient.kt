package com.tomyv.citytemperatureapp.view.api

import com.tomyv.citytemperatureapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val weatherBaseUrl = BuildConfig.BASE_URL

    private val client: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(weatherBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
    }

    fun buildClient(): WeatherEndpoints {
        return client
            .build().create(WeatherEndpoints::class.java)
    }
}