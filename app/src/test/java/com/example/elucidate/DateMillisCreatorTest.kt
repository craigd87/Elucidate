package com.example.elucidate

import org.junit.Assert.*

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateMillisCreatorTest {
    val sdf= SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    val simpleDate="2022/05/09 01:00:00"
    val simpleDateMillisValid=1652054400000
    val simpleDateMillisInvalidEdgePlus=1652054400001
    val simpleDateMillisInvalidEdgeMinus=165205439999
    val simpleDateMillisInvalidLarge=1000000000000
    val simpleDateMillisInvalidSmall: Long=0
    val simpleDateMillisInvalidNegative: Long=-1
    val dateParse= sdf.parse(simpleDate)
    val dateMillis=dateParse.time
    val finalDate= Date(dateMillis)
    val testDateValid=Date(simpleDateMillisValid)
    val testDateInvalid1=Date(simpleDateMillisInvalidEdgePlus)
    val testDateInvalid2=Date(simpleDateMillisInvalidEdgeMinus)
    val testDateInvalid3=Date(simpleDateMillisInvalidLarge)
    val testDateInvalid4=Date(simpleDateMillisInvalidSmall)
    val testDateInvalid5=Date(simpleDateMillisInvalidNegative)



    @Test
    fun getMillisecondsValid() {


        assertEquals(finalDate,testDateValid)
        assertNotEquals(finalDate,testDateInvalid1)
        assertNotEquals(finalDate,testDateInvalid2)
        assertNotEquals(finalDate,testDateInvalid3)
        assertNotEquals(finalDate,testDateInvalid4)
        assertNotEquals(finalDate,testDateInvalid5)
    }

}