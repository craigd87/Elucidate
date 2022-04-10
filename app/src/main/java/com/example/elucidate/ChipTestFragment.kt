package com.example.elucidate

import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import android.system.Os.open
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elucidate.databinding.FragmentChipTestBinding
import com.example.elucidate.databinding.FragmentStringTestBinding
import com.google.android.material.chip.Chip
import com.google.common.collect.Range.open
import java.io.File
import java.io.InputStream
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.AsynchronousServerSocketChannel.open
import java.nio.channels.AsynchronousSocketChannel.open
import java.nio.channels.DatagramChannel.open
import java.nio.channels.FileChannel.open
import java.nio.channels.Pipe.open
import java.nio.channels.Selector.open
import java.nio.channels.ServerSocketChannel.open
import java.nio.channels.SocketChannel.open


class ChipTestFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChipTestBinding.inflate(layoutInflater)

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



        binding.btnChipTest.setOnClickListener{
            /*val bufferedReader= File("src/main/res/stopwords.txt").bufferedReader()
            val words= mutableListOf<String>()
            bufferedReader.useLines { lines -> lines.forEach { words.add(it) } }
            words.forEach{ Log.i("Craig", "${it}")}*/

            //val inputStream: InputStream = File("stopwords.txt").inputStream()
            val lower= stringToSplit.lowercase()

val fileName= "stopwords.txt"

            /*val inputString = activity?.assets?.open("stopwords.txt")?.bufferedReader().use { it?.readText()
                 }

            Log.i("Craig", "${inputString}")*/
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
            }

            val finalText= mutableListOf<String>()

            for (item in moodTextList){
                /*for (subItem in stopWords ){
                    if (!subItem.contentEquals(item)){
                        finalText.add(item)
                    }*/
                if(item !in stopWords){
                    finalText.add(item)
                }
            }
            val finalTextDistinct=finalText.toSet().toList()
           /* val iterator=textList.iterator()
            for (item in lineList){
                for (subItem in textList){
                    while (iterator.hasNext()){
                        if (subItem.contentEquals(item)){
                            textList.remove(subItem)
                    }

                    }
                }
            }*/


           val printText= finalTextDistinct.filter{
                !it.isBlank()
                //!it.contentEquals("I")
                //!it.contentEquals("am")
            }



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

            chip.setChipBackgroundColorResource(R.color.white)
            chip.setCloseIconVisible(true);

            binding.chipGroupMain2.addView(chip)
            for (item in printText){
                val chip2= Chip(activity)
                chip2.text=item

                chip2.setChipBackgroundColorResource(R.color.white)
                chip2.setCloseIconVisible(true);
                binding.chipGroupMain2.addView(chip2)
            }
        }
        return binding.root
    }



}