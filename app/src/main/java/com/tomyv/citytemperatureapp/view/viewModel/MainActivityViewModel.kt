package com.tomyv.citytemperatureapp.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomyv.citytemperatureapp.view.model.WeatherResponse
import com.tomyv.citytemperatureapp.view.useCase.GetTemperatureUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Level.INFO
import java.util.logging.Level.SEVERE
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
                        logger.log(INFO, temperatureResponseMessage)
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    val failedRequestMessage = "Failed to get temperature"
                    temperatureLiveData.postValue(failedRequestMessage)
                    logger.log(SEVERE, failedRequestMessage)
                }
            })
        return temperatureLiveData
    }
}