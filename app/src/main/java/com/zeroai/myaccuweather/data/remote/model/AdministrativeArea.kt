package com.zeroai.myaccuweather.data.remote.model

@kotlinx.serialization.Serializable
data class AdministrativeArea(
    val ID: String,
    val LocalizedName: String
)