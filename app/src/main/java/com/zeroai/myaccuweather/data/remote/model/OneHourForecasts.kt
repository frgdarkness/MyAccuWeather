package com.zeroai.myaccuweather.data.remote.model

@kotlinx.serialization.Serializable
data class OneHourForecasts(
    val DateTime: String,
    val EpochDateTime: Int,
    val HasPrecipitation: Boolean,
    val IconPhrase: String,
    val IsDaylight: Boolean,
    val Link: String,
    val MobileLink: String,
    val PrecipitationProbability: Int,
    val Temperature: HourTemperature,
    val WeatherIcon: Int
)