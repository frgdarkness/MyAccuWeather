package com.zeroai.accuweatherhelper.data.remote.model

@kotlinx.serialization.Serializable
data class Maximum(
    val Unit: String,
    val UnitType: Float,
    val Value: Float
)