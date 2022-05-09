package com.example.elucidate

import java.text.SimpleDateFormat
import java.util.*

class DateMillisCreator {
    private val sdf= SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    fun getMilliseconds(simpleDate:String): Date{
        val dateParse= sdf.parse(simpleDate)
        val dateMillis=dateParse.time

        //create date objects from the milliseconds
        val finalDate= Date(dateMillis)
        return finalDate
    }

}