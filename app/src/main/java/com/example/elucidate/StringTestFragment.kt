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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StringTestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StringTestFragment : Fragment() {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StringTestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StringTestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}