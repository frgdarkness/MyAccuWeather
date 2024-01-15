package com.zeroai.accuweatherhelper

import java.text.SimpleDateFormat
import java.util.*

object DateConverter {

    const val ONE_DAY_SECOND = 24*60*60*1000

    fun getCurrentDate(): String {
        val time = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
        return dateFormat.format(time)
    }

    fun getNextDate(nextDate: Int): String {
        val time = System.currentTimeMillis() + nextDate*ONE_DAY_SECOND
        val dateFormat = SimpleDateFormat("dd-MM", Locale.getDefault())
        return dateFormat.format(time)
    }
}