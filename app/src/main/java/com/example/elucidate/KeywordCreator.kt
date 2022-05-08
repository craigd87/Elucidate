package com.example.elucidate

import android.util.Log

class KeywordCreator {

    private val delim1=" "
    private val delim2="."
    private val delim3="?"
    private val delim4="-"
    private val delim5="!"
    private val delim6="/"
    private val delim7=";"
    private val delim8=","
    private val delim9=". "
    private val delim10=".  "
    private val delim11="? "
    private val delim12="! "
    private val delim13="; "
    private val delim14=", "

    fun createKeywordList(stringToSplit: String, stopWords: MutableList<String>): List<String>{

        val moodText= stringToSplit.split(delim1,delim2,delim3,delim4,delim5,delim6,delim7,delim8,delim9,delim10,
            delim11,delim12,delim13,delim14)

        val moodTextList= mutableListOf<String>()

        moodText.forEach{
            moodTextList.add(it)
        }

        val finalText= mutableListOf<String>()

        for (item in moodTextList){

            if(item !in stopWords){
                finalText.add(item)
            }
        }

        val finalTextDistinct=finalText.toSet().toList()
        val printText= finalTextDistinct.filter{
            !it.isBlank()
        }

        return printText
    }

}