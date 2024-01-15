package com.zeroai.myaccuweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zeroai.accuweatherhelper.AccuWeatherHelper
import com.zeroai.myaccuweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initEvent()
        setContentView(binding.root)
    }

    private fun initEvent() {
        binding.apply {
            btnGetDailyWeather.setOnClickListener {
                val intent = Intent(this@MainActivity, WeatherActivity::class.java)
                intent.putExtra("LOCATION_NAME_DATA", edtCity.text.toString())
//                startActivity(intent)
//                EasyToast.showShortToast("Hello", this@MainActivity)
                AccuWeatherHelper.showWeatherDetailByLocationName(edtCity.text.toString(), this@MainActivity)
            }
        }
    }
}