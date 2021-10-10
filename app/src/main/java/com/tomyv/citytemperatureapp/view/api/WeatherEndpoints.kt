package com.tomyv.citytemperatureapp.view.api

import com.tomyv.citytemperatureapp.view.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherEndpoints {
    @GET("weather")
    fun getCurrentWeatherData(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("appid") app_id: String
    ): Call<WeatherResponse>
}