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
    fun getDateDaysAgo(date: Date, daysAgo: Int): Date{
        val simpleDate=SimpleDateFormat("yyyy/MM/dd").format(date)
        val simpleDateStart="$simpleDate 00:00:00"
        val dateParse=sdf.parse(simpleDateStart)
        val dateParseMillis=dateParse.time
        val finalDateMillis=dateParseMillis-(43200000*daysAgo)
        val finalDate= Date(finalDateMillis)
        return finalDate

    }
}