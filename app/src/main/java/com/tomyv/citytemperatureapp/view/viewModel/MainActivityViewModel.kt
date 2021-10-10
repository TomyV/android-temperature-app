package com.tomyv.citytemperatureapp.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomyv.citytemperatureapp.view.model.WeatherResponse
import com.tomyv.citytemperatureapp.view.useCase.GetTemperatureUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.logging.Logger

class MainActivityViewModel : ViewModel() {
    fun getTemperature(city: String): LiveData<String> {
        val logger = Logger.getGlobal()
        val temperatureLiveData = MutableLiveData<String>()

        GetTemperatureUseCase.getCurrentTemperature(city)
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>, response: Response<WeatherResponse>
                ) {
                    if (response.code() == 200) {
                        val temperature = response.body()!!.main!!.temp
                        val temperatureResponseMessage = "Temperature in $city: $temperature°C"
                        temperatureLiveData.postValue(temperatureResponseMessage)
                        logger.info(temperatureResponseMessage)
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    val failedRequestMessage = "Failed to get temperature"
                    temperatureLiveData.postValue(failedRequestMessage)
                    logger.severe(failedRequestMessage)
                }
            })
        return temperatureLiveData
    }

    fun getCurrentTime(): MutableLiveData<String> {
        val timeLiveData = MutableLiveData<String>()
        val currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        timeLiveData.postValue("Last updated: $currentTime")
        return timeLiveData
    }
}