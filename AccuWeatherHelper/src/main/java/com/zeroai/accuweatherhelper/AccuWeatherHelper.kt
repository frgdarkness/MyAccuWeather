package com.zeroai.accuweatherhelper

import android.R
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.zeroai.accuweatherhelper.data.remote.AccuWeatherApi
import com.zeroai.accuweatherhelper.data.remote.model.CityResponse
import com.zeroai.accuweatherhelper.data.remote.model.DailyWeatherResponse
import com.zeroai.accuweatherhelper.data.remote.model.OneHourForecasts
import com.zeroai.accuweatherhelper.data.remote.model.getMaxCelsius
import com.zeroai.accuweatherhelper.data.remote.model.getMinCelsius
import com.zeroai.accuweatherhelper.databinding.DialogWeatherBinding
import com.zeroai.myaccuweather.data.Helper.convertToCelsius
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object AccuWeatherHelper {

    private val weatherService by lazy { AccuWeatherApi.create() }

    /*
        Note: Auto select first location match with location name
     */
    fun showWeatherDetailByLocationName(locationName: String, context: Context) {
        val dialog = Dialog(context, R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        val binding = DialogWeatherBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.show()
        val adapter = DailyForecastAdapter(context)
        binding.recyclerView.adapter = adapter
        binding.tvLocation.text = locationName
        CoroutineScope(Dispatchers.IO).launch {
            findLocation(binding.tvLocation.text.toString()) {
                val locationId = it.firstOrNull()?.Key ?: ""
                val LocalizedName = it.firstOrNull()?.LocalizedName ?: ""
                withContext(Dispatchers.Main) {
                    binding.tvLocation.text = LocalizedName
                }
                loadCurrentForecast(locationId) { oneHourForecasts ->
                    withContext(Dispatchers.Main) {
                        binding.tvCurrentTemp.text =
                            "${oneHourForecasts.Temperature.Value.convertToCelsius()}°C"
                    }
                }

                load5DayWeather(locationId) { dailyWeatherReponse ->
                    val todayForecast = dailyWeatherReponse.DailyForecasts.first()
                    val minCelsius = todayForecast.Temperature.getMinCelsius()
                    val maxCelsius = todayForecast.Temperature.getMaxCelsius()
                    withContext(Dispatchers.Main) {
                        adapter.loadData(dailyWeatherReponse.DailyForecasts)
                        binding.apply {
                            tvStatus.text = dailyWeatherReponse.Headline.Category
                            tvTempMinMax.text = "$minCelsius°/$maxCelsius°"
                        }
                    }
                }
            }
        }
    }

    fun showWeatherDetailByLocationId(locationId: String, locationName: String, context: Context) {
        val dialog = Dialog(context, R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        val binding = DialogWeatherBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.show()
        val adapter = DailyForecastAdapter(context)
        binding.recyclerView.adapter = adapter
        binding.tvLocation.text = locationName
        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                binding.tvLocation.text = locationName
            }
            loadCurrentForecast(locationId) { oneHourForecasts ->
                withContext(Dispatchers.Main) {
                    binding.tvCurrentTemp.text =
                        "${oneHourForecasts.Temperature.Value.convertToCelsius()}°C"
                }
            }

            load5DayWeather(locationId) { dailyWeatherReponse ->
                val todayForecast = dailyWeatherReponse.DailyForecasts.first()
                val minCelsius = todayForecast.Temperature.getMinCelsius()
                val maxCelsius = todayForecast.Temperature.getMaxCelsius()
                withContext(Dispatchers.Main) {
                    adapter.loadData(dailyWeatherReponse.DailyForecasts)
                    binding.apply {
                        tvStatus.text = dailyWeatherReponse.Headline.Category
                        tvTempMinMax.text = "$minCelsius°/$maxCelsius°"
                    }
                }
            }

        }
    }

    /*
        Return list of location data match with location name, you can get placeId from response to run api get weather
     */
    suspend fun autoCompleteLocation(location: String): List<CityResponse> {
        return weatherService.getCityInfo(location)
    }

    private fun findLocation(location: String, onResult: suspend (List<CityResponse>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val responses = weatherService.getCityInfo(location)
            onResult(responses)
        }
    }

    private fun load5DayWeather(locationId: String, onResult: suspend (DailyWeatherResponse) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherService.getFiveDayWeather(locationId)?.let { response ->
                onResult(response)
            }
        }
    }

    private fun loadCurrentForecast(locationId: String, onResult: suspend (OneHourForecasts) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherService.getCurrentWeather(locationId)?.let { oneHourForecasts ->
                onResult(oneHourForecasts)
            }
        }
    }
}