package com.zeroai.accuweatherhelper.data.remote.model

import com.zeroai.accuweatherhelper.data.remote.model.HourTemperature

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