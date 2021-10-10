package com.tomyv.citytemperatureapp.view.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main")
    var main: Main? = Main()
)
