package com.example.elucidate.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentGeneralEntryBinding
import com.example.elucidate.globalGeneralEntryDetails
import com.example.elucidate.globalMoodEntryDetails
import com.example.elucidate.globalUser

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */

/**
 * A simple [Fragment] subclass.
 * Use the [GeneralEntryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GeneralEntryFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentGeneralEntryBinding.inflate(layoutInflater)

        binding.tvEnterThoughts.text = "Hi " + globalUser.name+"! please enter your thoughts"

        binding.btnSubmitEntry.setOnClickListener { view: View ->

            globalGeneralEntryDetails.textEntry=binding.etEntry.text.toString()

            view.findNavController().navigate(R.id.action_generalEntryFragment_to_identifyGeneralKeywordsFragment)

        }



    return binding.root
    }


}