package com.zeroai.myaccuweather.data

object Helper {

    fun Float.convertToCelsius(): Int {
        return (this.toInt() - 32)*5/9
    }
}