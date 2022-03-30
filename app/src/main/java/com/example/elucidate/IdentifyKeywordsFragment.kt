package com.example.elucidate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.elucidate.databinding.FilterChipBinding
import com.example.elucidate.databinding.FragmentIdentifyKeywordsBinding
import com.example.elucidate.databinding.FragmentStringTestBinding
import com.google.android.material.chip.Chip
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*


class IdentifyKeywordsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentIdentifyKeywordsBinding.inflate(layoutInflater)

        //take date string and parse to obtain value in milliseconds from

        /*//val simpleDate1= "2022/03/29 00:00:00"
        //val simpleDate2= "2022/03/30 23:59:59"
        use above for searching specific dates
         */
        val formatCurrentDateWithoutTime= SimpleDateFormat("yyyy/MM/dd")

        //get current date without time
        val currentDateWithoutTime= formatCurrentDateWithoutTime.format(Date())

        //set values from start to end of current date as String and parse to get value in milliseconds
        val simpleCurrentDateStart= "$currentDateWithoutTime 00:00:00"
        val simpleCurrentDateEnd= "$currentDateWithoutTime 23:59:59"
        val sdf= SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val dateStartParse= sdf.parse(simpleCurrentDateStart)
        val dateEndParse= sdf.parse(simpleCurrentDateEnd)
        val dateStartMillis=dateStartParse.time
        val dateEndMillis=dateEndParse.time

        //create date objects from the milliseconds
        val finalDateStart= Date(dateStartMillis)
        val finalDateEnd= Date(dateEndMillis)

        //query Firestore to find mood entries from the current date
        val queryRef = FirebaseUtils().fireStoreDatabase.collection("userMoods")
        var stringForKeywords=""
        queryRef.whereGreaterThanOrEqualTo("time", finalDateStart).whereLessThanOrEqualTo("time", finalDateEnd)
            .orderBy("time", Query.Direction.DESCENDING).limit(1)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if(document!=null){
                        Log.d("exist", "DocumentSnapshot data: ${document.data}")
                        stringForKeywords= document.getString("moodEntry").toString()
                        Log.d("keywords", stringForKeywords)
                        /* this retrieves one entry
                        binding.tvTestRead.text = mood
                         */
                        /*
                        for test purposes to show how tv can display entries, tv no longer exists
                        val text= TextView(activity)
                        text.text = mood
                        binding.textLayout.addView(text)
                         */

                        //var resultText= ""
                        val delim1=" "
                        val delim2="."
                        val delim3="?"
                        val delim4="-"
                        val delim5="!"
                        val delim6="/"
                        val delim7=";"
                        val delim8=","
                        val delim9=". "
                        val delim10=".  "
                        val delim11="? "
                        val delim12="! "
                        val delim13="; "
                        val delim14=", "
                        //val delim15= "\n"
                        //val delimRegex= Regex()

                        val lower= stringForKeywords.lowercase()
                        Log.d("lowwwwer", lower)

                        val fileName= "stopwords.txt"
                        val stopWords = mutableListOf<String>()
                        val inputString = activity?.assets?.open(fileName)?.bufferedReader()?.useLines { lines -> lines.forEach { stopWords.add(it)} }
                        stopWords.forEach{
                            Log.i("Craig",  it)
                        }

                        val moodText= lower.split(delim1,delim2,delim3,delim4,delim5,delim6,delim7,delim8,delim9,delim10,
                            delim11,delim12,delim13,delim14)
                        val moodTextList= mutableListOf<String>()
                        moodText.forEach{
                            moodTextList.add(it)
                            Log.d("niles", it)
                        }

                        val finalText= mutableListOf<String>()

                        for (item in moodTextList){
                            /*for (subItem in stopWords ){
                                if (!subItem.contentEquals(item)){
                                    finalText.add(item)
                                }*/
                            Log.d("frasier", item)
                            if(item !in stopWords){
                                finalText.add(item)
                                Log.d("witch", item)
                            }
                        }
                        val finalTextDistinct=finalText.toSet().toList()

                        Log.d("Potter", "$finalTextDistinct")


                        val printText= finalTextDistinct.filter{
                            !it.isBlank()
                            //!it.contentEquals("I")
                            //!it.contentEquals("am")

                        }
                        Log.d("duck", "$printText")


                        /*var resultTestMut=resultText.toMutableList()
                        for(item in resultTestMut){
                            if(item.contentEquals(" ")){
                                resultTestMut.remove(item)
                            }
                        }
                        val length= resultTestMut.size*/
                        //Log.i("stringTest", "$printText")
                        //binding.textViewTestResult.text=printText.toString()
                        //binding.btnSplitTest.text=printText.toString()

                        //val chip= Chip(activity)
                        //chip.text="HELLO!"

                        //chip.setChipBackgroundColorResource(R.color.white)
                        //chip.setCloseIconVisible(true);

                        //binding.cgKeywords.addView(chip)

                        for (item in printText){
                            Log.d("chips", item)
                            //val chip= Chip(activity)
                            val chip=inflater.inflate(R.layout.filter_chip, binding.cgKeywords, false) as Chip
                            chip.text=item


                            chip.setChipBackgroundColorResource(R.color.white)
                            //chip.setCloseIconVisible(true);
                            binding.cgKeywords.addView(chip)
                        }

                        binding.btnCheckWords.setOnClickListener { view: View ->
                            val ids = binding.cgKeywords.checkedChipIds
                            for (id in ids) {
                                val wordSelection =
                                    binding.cgKeywords.findViewById<Chip>(id).text.toString()
                                Log.d("favourites", wordSelection)
                            }
                        }
                    }else{
                        //binding.tvTestRead.text = "NULL"
                        stringForKeywords="none found"
                    }

                }

            }



        return binding.root
    }


}