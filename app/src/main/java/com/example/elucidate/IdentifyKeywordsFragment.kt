package com.example.elucidate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.elucidate.databinding.FragmentIdentifyKeywordsBinding
import com.example.elucidate.databinding.FragmentStringTestBinding
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
        val simpleDate1= "2022/03/29 00:00:00"
        val simpleDate2= "2022/03/29 23:59:59"
        val sdf= SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date1Parse= sdf.parse(simpleDate1)
        val date2Parse= sdf.parse(simpleDate2)
        val date1Millis=date1Parse.time
        val date2Millis=date2Parse.time

        //create date objects from the milliseconds
        val finalDate1= Date(date1Millis)
        val finalDate2= Date(date2Millis)


        val queryRef = FirebaseUtils().fireStoreDatabase.collection("userMoods")

        queryRef.whereGreaterThanOrEqualTo("time", finalDate1).whereLessThan("time", finalDate2)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if(document!=null){
                        Log.d("exist", "DocumentSnapshot data: ${document.data}")
                        //moodRating = document.getString("moodRating").toString()
                        val mood= document.getString("moodEntry").toString()
                        /* this retrieves one entry
                        binding.tvTestRead.text = mood
                         */
                        val text= TextView(activity)
                        text.text = mood
                        binding.textLayout.addView(text)
                    }else{
                        binding.tvTestRead.text = "NULL"
                    }

                }

            }

        return binding.root
    }


}