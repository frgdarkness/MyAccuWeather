package com.zeroai.myaccuweather.data.remote.model

@kotlinx.serialization.Serializable
data class HourTemperature(
    val Unit: String,
    val UnitType: Float,
    val Value: Float
)