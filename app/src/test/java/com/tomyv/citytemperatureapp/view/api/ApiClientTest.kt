package com.tomyv.citytemperatureapp.view.api

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ApiClientTest {
    @Test
    fun clientBuildSuccessful() {
        val client = ApiClient.buildClient()

        assertThat(client).isNotNull()
        assertThat(client).isInstanceOf(WeatherEndpoints::class.java)
    }
}
