package com.tomyv.citytemperatureapp.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tomyv.citytemperatureapp.R
import com.tomyv.citytemperatureapp.view.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showTemperatureInfo()

        val swipeContainer: SwipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeContainer.setOnRefreshListener {
            showTemperatureInfo()
            swipeContainer.isRefreshing = false
        }
    }

    private fun showTemperatureInfo() {
        val timeContainer: TextView? = findViewById(R.id.textView)
        val weatherContainer: TextView? = findViewById(R.id.textView2)
        val city = getString(R.string.city_name)
        val mainActivityViewModel: MainActivityViewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getTemperature(city).observe(this,
            { temperatureInfo -> weatherContainer!!.text = temperatureInfo })
        mainActivityViewModel.getCurrentTime()
            .observe(this, { timeInfo -> timeContainer!!.text = timeInfo })
    }
}