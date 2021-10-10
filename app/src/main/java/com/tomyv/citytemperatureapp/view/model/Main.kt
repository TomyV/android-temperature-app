package com.tomyv.citytemperatureapp.view.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    val temp: Float = 0.0F
)
