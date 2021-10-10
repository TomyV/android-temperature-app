package com.tomyv.citytemperatureapp.view.useCase

import com.tomyv.citytemperatureapp.BuildConfig
import com.tomyv.citytemperatureapp.view.api.ApiClient
import com.tomyv.citytemperatureapp.view.model.WeatherResponse
import retrofit2.Call

object GetTemperatureUseCase {
    fun getCurrentTemperature(city: String): Call<WeatherResponse> {
        return ApiClient.buildClient().getCurrentWeatherData(city, "metric", BuildConfig.API_KEY)
    }
}