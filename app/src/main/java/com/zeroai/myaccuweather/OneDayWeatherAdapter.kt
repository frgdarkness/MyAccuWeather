package com.zeroai.myaccuweather

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zeroai.myaccuweather.data.remote.model.DailyForecast
import com.zeroai.myaccuweather.data.remote.model.getMaxCelsius
import com.zeroai.myaccuweather.data.remote.model.getMinCelsius
import com.zeroai.myaccuweather.databinding.ItemViewOneDayBinding

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
            tvTempMinMax.text = "$min°/$max°"
        }
    }

    fun loadData(data: List<DailyForecast>) {
        weatherData.clear()
        weatherData.addAll(data)
        notifyDataSetChanged()
    }
}