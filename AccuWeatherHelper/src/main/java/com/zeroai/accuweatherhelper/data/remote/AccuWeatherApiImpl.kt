package com.zeroai.accuweatherhelper.data.remote

import android.util.Log
import com.zeroai.accuweatherhelper.data.remote.model.CityResponse
import com.zeroai.accuweatherhelper.data.remote.model.DailyWeatherResponse
import com.zeroai.accuweatherhelper.data.remote.model.OneHourForecasts
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AccuWeatherApiImpl(
    private val client: HttpClient
) : AccuWeatherApi {
    override suspend fun getCityInfo(cityName: String): List<CityResponse> {
        return try {

            val response: HttpResponse = client.request("http://dataservice.accuweather.com/locations/v1/cities/autocomplete") {
                method = HttpMethod.Get
                url {
                    parameters.append("apikey", "mvsZHXrLqgTixK4WGFWyexBzHSpcD1mb")
                    parameters.append("q", cityName)
                    parameters.append("language", "en-us")

                }
            }
            val responseData: String = response.body()
            Log.d("asd123", "responseData = $responseData")
            var cityResponses: List<CityResponse>? = null
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                explicitNulls = false
            }
            cityResponses = json.decodeFromString(responseData)
            Log.d("asd123", "responseData = $cityResponses")
            cityResponses ?: emptyList()
        } catch (e : Exception) {
            Log.d("asd123", "gotException: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getDailyWeather(locationCode: String): DailyWeatherResponse? {
        return try {
            val response: HttpResponse = client.request("http://dataservice.accuweather.com/forecasts/v1/daily/1day/$locationCode") {
                method = HttpMethod.Get
                url {
                    parameters.append("apikey", "mvsZHXrLqgTixK4WGFWyexBzHSpcD1mb")
                    parameters.append("details", "false")
                    parameters.append("metric", "false")
                }
            }
            val responseData: String = response.body()
            Log.d("asd123", "responseData = $responseData")
            var dailyWeatherResponse: DailyWeatherResponse? = null
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                explicitNulls = false
            }
            dailyWeatherResponse = json.decodeFromString(responseData)
            Log.d("asd123", "responseData = $dailyWeatherResponse")
            dailyWeatherResponse
        } catch (e : Exception) {
            Log.d("asd123", "gotException: ${e.message}")
            null
        }
    }

    override suspend fun getFiveDayWeather(locationCode: String): DailyWeatherResponse? {
        return try {
            val response: HttpResponse = client.request("http://dataservice.accuweather.com/forecasts/v1/daily/5day/$locationCode") {
                method = HttpMethod.Get
                url {
                    parameters.append("apikey", "mvsZHXrLqgTixK4WGFWyexBzHSpcD1mb")
                    parameters.append("details", "false")
                    parameters.append("metric", "false")
                }
            }
            val responseData: String = response.body()
            Log.d("asd123", "responseData = $responseData")
            var dailyWeatherResponse: DailyWeatherResponse? = null
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                explicitNulls = false
            }
            dailyWeatherResponse = json.decodeFromString(responseData)
            Log.d("asd123", "responseData = $dailyWeatherResponse")
            dailyWeatherResponse
        } catch (e : Exception) {
            Log.d("asd123", "gotException: ${e.message}")
            null
        }
    }

    override suspend fun getCurrentWeather(locationCode: String): OneHourForecasts? {
        return try {
            val response: HttpResponse = client.request("http://dataservice.accuweather.com/forecasts/v1/hourly/1hour/$locationCode") {
                method = HttpMethod.Get
                url {
                    parameters.append("apikey", "mvsZHXrLqgTixK4WGFWyexBzHSpcD1mb")
                    parameters.append("details", "false")
                    parameters.append("metric", "false")
                }
            }
            val responseData: String = response.body()
            Log.d("asd123", "responseData = $responseData")
            var dailyWeatherResponse: List<OneHourForecasts> = emptyList()
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                explicitNulls = false
            }
            dailyWeatherResponse = json.decodeFromString(responseData)
            Log.d("asd123", "responseData = $dailyWeatherResponse")
            dailyWeatherResponse.firstOrNull()
        } catch (e : Exception) {
            Log.d("asd123", "gotException: ${e.message}")
            null
        }
    }
}