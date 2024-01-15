package com.zeroai.myaccuweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zeroai.myaccuweather.data.Helper.convertToCelsius
import com.zeroai.myaccuweather.data.remote.AccuWeatherApi
import com.zeroai.myaccuweather.data.remote.model.getMaxCelsius
import com.zeroai.myaccuweather.data.remote.model.getMinCelsius
import com.zeroai.myaccuweather.databinding.ActivityWeatherBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private val weatherService by lazy { AccuWeatherApi.create() }
    private var adapter: DailyForecastAdapter? = null
    private var locationId = ""
    private var locationName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        val locationName = intent.getStringExtra("LOCATION_NAME_DATA") ?: ""
        findLocation(locationName)
//        val locationId = intent.getStringExtra("LOCATION_ID_DATA") ?: "0"
//        val locationName = intent.getStringExtra("LOCATION_NAME_DATA")
        adapter = DailyForecastAdapter(this)
        binding.recyclerView.adapter = adapter

        setContentView(binding.root)
    }

    private fun findLocation(location: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val responses = weatherService.getCityInfo(location)
            responses.forEach {
                Log.d("asd123", "city: $it")
            }
            locationId = responses.firstOrNull()?.Key ?: ""
            locationName = responses.firstOrNull()?.LocalizedName ?: ""
            withContext(Dispatchers.Main) {
                binding.tvLocation.text = locationName
            }
            loadWeather()
        }
    }

    private fun loadWeather() {
        CoroutineScope(Dispatchers.IO).launch {
            weatherService.getFiveDayWeather(locationId)?.let { response ->
                withContext(Dispatchers.Main) {
                    adapter?.loadData(response.DailyForecasts)
                    val todayForecast = response.DailyForecasts.first()
                    val minCelsius = todayForecast.Temperature.getMinCelsius()
                    val maxCelsius = todayForecast.Temperature.getMaxCelsius()
                    binding.apply {
                        tvStatus.text = response.Headline.Category
                        tvTempMinMax.text = "$minCelsius°/$maxCelsius°"
                    }
                }
            }
            weatherService.getCurrentWeather(locationId)?.let { oneHourForecasts ->
                withContext(Dispatchers.Main) {
                    binding.tvCurrentTemp.text = "${oneHourForecasts.Temperature.Value.convertToCelsius()}°C"
                }
            }
        }
    }
}