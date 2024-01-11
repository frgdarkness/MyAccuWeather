package com.zeroai.myaccuweather.data.remote.model

@kotlinx.serialization.Serializable
data class Country(
    val ID: String,
    val LocalizedName: String
)