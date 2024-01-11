package com.zeroai.myaccuweather.data.remote

import com.zeroai.myaccuweather.data.remote.model.CityResponse
import com.zeroai.myaccuweather.data.remote.model.DailyWeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

interface AccuWeatherApi {

    companion object {
        const val API_KEY = "mvsZHXrLqgTixK4WGFWyexBzHSpcD1mb"

        fun create(): AccuWeatherApi {
            return AccuWeatherApiImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        logger = Logger.DEFAULT
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json()
                    }
                }
            )
        }
    }

    suspend fun getCityInfo(cityName: String): List<CityResponse>

    suspend fun getDailyWeather(locationCode: String): DailyWeatherResponse?
}