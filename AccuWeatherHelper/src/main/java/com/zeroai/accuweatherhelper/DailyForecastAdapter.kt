package com.zeroai.accuweatherhelper

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zeroai.accuweatherhelper.data.remote.model.DailyForecast
import com.zeroai.accuweatherhelper.data.remote.model.getMaxCelsius
import com.zeroai.accuweatherhelper.data.remote.model.getMinCelsius
import com.zeroai.accuweatherhelper.databinding.ItemViewOneDayBinding

class DailyForecastAdapter(private val context: Context) : RecyclerView.Adapter<DailyForecastAdapter.OneDayWeatherViewHolder>() {

    private val weatherData = mutableListOf<DailyForecast>()

    class OneDayWeatherViewHolder(val binding: ItemViewOneDayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneDayWeatherViewHolder {
        val binding = ItemViewOneDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OneDayWeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    override fun onBindViewHolder(holder: OneDayWeatherViewHolder, position: Int) {
        val data = weatherData[position]
        holder.binding.apply {
            tvDate.text = when (position) {
                0 -> "Today"
                else -> DateConverter.getNextDate(position+1)
            }
            val min = data.Temperature.getMinCelsius()
            val max = data.Temperature.getMaxCelsius()
            val iconId = data.Day.Icon
            val resourceIcon = iconId.getWeatherIcon()
            Log.d("asd123", "icon = $iconId - resourceIcon = $resourceIcon")
            imageIcon.setImageResource(resourceIcon)
            tvTempMinMax.text = "$min°/$max°"
        }
    }

    fun loadData(data: List<DailyForecast>) {
        weatherData.clear()
        weatherData.addAll(data)
        notifyDataSetChanged()
    }

    fun Int.getWeatherIcon(): Int = when(this) {
        1, 2, 3, 4 -> R.drawable.sun1234
        5, 6 -> R.drawable.sun_cloud_56
        7 -> R.drawable.cloudy7
        8, 11, 37, 38 -> R.drawable.cloudy8_11_37_38
        12, 18 -> R.drawable.rain12
        13, 14, 16, 17 -> R.drawable.rain_with_sun_13_14_16_17
        19, 43 -> R.drawable.tornado19_43
        20, 21 -> R.drawable.fog20_21
        22, 24 -> R.drawable.snow_22_26
        23 -> R.drawable.snow_cloudy_23
        25, 29 -> R.drawable.rain_snow_25_29
        26 -> R.drawable.hail_26
        33 -> R.drawable.clear33
        34, 35, 36 -> R.drawable.night_34_35_36
        39, 40 -> R.drawable.night_rain_39_40
        41, 42 -> R.drawable.night_rain_41_42
        44 -> R.drawable.night_snow_44
        else -> R.drawable.sun1234
    }
}