package com.zeroai.myaccuweather.data.remote.model

@kotlinx.serialization.Serializable
data class Minimum(
    val Unit: String,
    val UnitType: Float,
    val Value: Float
)