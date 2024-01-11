package com.zeroai.myaccuweather.data.remote.model

@kotlinx.serialization.Serializable
data class DailyForecast(
    val Date: String,
    val EpochDate: Int,
    val Temperature: Temperature,
    val Day: Day,
    val Night: Night,
    val Sources: List<String>,
    val MobileLink: String,
    val Link: String
)