package com.example.elucidate

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.elucidate.databinding.FragmentSignUpBinding
import com.example.elucidate.databinding.FragmentStringTestBinding
import com.google.android.material.chip.Chip


class StringTestFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentStringTestBinding.inflate(layoutInflater)

        val stringToSplit= "Here I am testing my string?\n I want, to make sure/clarify \n" +
                "                    that it works! If it doesn\'t- \n I will be angry; and I will be annoyed."
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

        binding.btnSplitTest.setOnClickListener{
            val resultText= stringToSplit.split(delim1,delim2,delim3,delim4,delim5,delim6,delim7,delim8,delim9,delim10,
            delim11,delim12,delim13,delim14)
            val printText= resultText.filter{!it.isBlank()}
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
            val chip= Chip(activity)
            chip.text="HELLO!"

            chip.setChipBackgroundColorResource(R.color.black)
            chip.setCloseIconVisible(true);

            binding.chipGroupMain.addView(chip)
        }

        return binding.root
    }


}