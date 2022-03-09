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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChipTestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChipTestFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

val fileName= "stopwords.txt"

            /*val inputString = activity?.assets?.open("stopwords.txt")?.bufferedReader().use { it?.readText()
                 }

            Log.i("Craig", "${inputString}")*/
            val lineList = mutableListOf<String>()
            val inputString = activity?.assets?.open(fileName)?.bufferedReader()?.useLines { lines -> lines.forEach { lineList.add(it)} }
            lineList.forEach{
                Log.i("Craig",  it)
            }

            val resultText= stringToSplit.split(delim1,delim2,delim3,delim4,delim5,delim6,delim7,delim8,delim9,delim10,
                delim11,delim12,delim13,delim14)
            val textList= mutableListOf<String>()
            resultText.forEach{
                textList.add(it)
            }

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


           val printText= textList.filter{
                !it.isBlank()
                //!it.contentEquals("I")
                //!it.contentEquals("am")
            }

            val distinct=printText.toSet().toList()

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
            for (item in distinct){
                val chip2= Chip(activity)
                chip2.text=item

                chip2.setChipBackgroundColorResource(R.color.white)
                chip2.setCloseIconVisible(true);
                binding.chipGroupMain2.addView(chip2)
            }
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChipTestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChipTestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}