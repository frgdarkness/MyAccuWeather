package com.zeroai.myaccuweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zeroai.myaccuweather.data.remote.AccuWeatherApi
import com.zeroai.myaccuweather.databinding.ActivityMainBinding
import java.util.logging.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherService by lazy { AccuWeatherApi.create() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        initEvent()
        setContentView(binding.root)
    }

    private fun initEvent() {
        binding.apply {
            btnFindCity.setOnClickListener {
                val city = edtCity.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val responses = weatherService.getCityInfo(city)
                    responses.forEach {
                        Log.d("asd123", "city: $it")
                    }
                    val key = responses.firstOrNull()?.Key
                    edtCityLocation.setText(key.toString())
                }
            }
            btnGetDailyWeather.setOnClickListener {
                val location = edtCityLocation.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val responses = weatherService.getDailyWeather(location)
                    Log.d("asd123", "weather: $responses")
                }
            }
        }
    }
}