package com.zeroai.myaccuweather.data.remote.model

@kotlinx.serialization.Serializable
data class Day(
    val HasPrecipitation: Boolean = false,
    val Icon: Int = 0,
    val IconPhrase: String = "",
    val PrecipitationIntensity: String = "",
    val PrecipitationType: String = ""
)