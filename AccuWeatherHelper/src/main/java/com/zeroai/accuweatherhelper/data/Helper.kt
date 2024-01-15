package com.zeroai.myaccuweather.data

import com.zeroai.accuweatherhelper.R

object Helper {

    fun Float.convertToCelsius(): Int {
        return (this.toInt() - 32)*5/9
    }


}