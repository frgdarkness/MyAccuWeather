package com.zeroai.accuweatherhelper.data.remote.model

@kotlinx.serialization.Serializable
data class CityResponse(
    val AdministrativeArea: AdministrativeArea,
    val Country: Country,
    val Key: String,
    val LocalizedName: String,
    val Rank: Int,
    val Type: String,
    val Version: Int
)