package com.zeroai.myaccuweather.data.remote.model

import com.zeroai.myaccuweather.data.Helper.convertToCelsius

@kotlinx.serialization.Serializable
data class Temperature(
    val Minimum: Minimum,
    val Maximum: Maximum,
)

fun Temperature.getMinCelsius() = this.Minimum.Value.convertToCelsius()
fun Temperature.getMaxCelsius() = this.Maximum.Value.convertToCelsius()