package com.zeroai.myaccuweather.data.remote.model

@kotlinx.serialization.Serializable
data class Temperature(
    val Minimum: Minimum,
    val Maximum: Maximum,
)