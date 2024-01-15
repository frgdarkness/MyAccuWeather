package com.zeroai.accuweatherhelper.data.remote.model

@kotlinx.serialization.Serializable
data class DailyWeatherResponse(
    val Headline: Headline,
    val DailyForecasts: List<DailyForecast>,
)