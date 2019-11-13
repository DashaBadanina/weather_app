package com.example.weatherapp.domane

import java.text.SimpleDateFormat

object DateUtils {

    fun getFormattedDay(date: Long): String {
        val df = java.util.Date(date * 1000)
        return SimpleDateFormat("EEE, MMM d").format(df)
    }

    fun getFormattedTime(date: Long): String {
        val df = java.util.Date(date * 1000)
        return SimpleDateFormat("h:mm a").format(df)
    }
}