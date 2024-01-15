package com.zeroai.accuweatherhelper.data.remote

import com.zeroai.accuweatherhelper.data.remote.model.CityResponse
import com.zeroai.accuweatherhelper.data.remote.model.DailyWeatherResponse
import com.zeroai.accuweatherhelper.data.remote.model.OneHourForecasts
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

    suspend fun getFiveDayWeather(locationCode: String): DailyWeatherResponse?

    suspend fun getCurrentWeather(locationCode: String): OneHourForecasts?
}