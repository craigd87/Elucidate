package com.example.elucidate.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.elucidate.*
import com.example.elucidate.dto.Mood
import com.example.elucidate.databinding.FragmentIdentifyPositivesBinding
import com.google.android.material.chip.Chip
import com.google.firebase.Timestamp
import java.util.*


class IdentifyPositivesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentIdentifyPositivesBinding.inflate(layoutInflater)

        val chosenPositives= mutableListOf<String>()

        for (item in globalNonTriggersList){
            Log.d("triggerChips", item)
            //val chip= Chip(activity)
            val chip=inflater.inflate(R.layout.filter_chip, binding.cgPositives, false) as Chip
            chip.text=item


            chip.setChipBackgroundColorResource(R.color.white)
            //chip.setCloseIconVisible(true);
            binding.cgPositives.addView(chip)
        }

        binding.btnComplete.setOnClickListener { view: View ->
            val ids = binding.cgPositives.checkedChipIds

            for (id in ids) {
                val wordSelection =
                    binding.cgPositives.findViewById<Chip>(id).text.toString()
                //chosenPositives.add(binding.cgPositives.findViewById<Chip>(id).text.toString())
                //globalTriggerWordsList.add(binding.cgPositives.findViewById<Chip>(id).text.toString())
                globalPositiveWordsList.add(wordSelection)

                Log.d("Mpos", globalPositiveWordsList.toString())


                //val action=IdentifyKeywordsFragmentDirections.actionIdentifyKeywordsFragmentToKeywordQualityFragment(chosenKeywords)
            }
            /*for (item in globalKeywordsList){
                if(item !in globalTriggerWordsList){
                    globalNonTriggersList.add(item)
                }
            }*/
            val id= viewModel.getCurrentUserId()
            val time= Timestamp(Date())
            val mood= Mood("$id", globalMoodEntry,"$globalMoodRating", globalKeywordsList, globalTriggerWordsList, globalPositiveWordsList, time)
            Log.d("fandabidosi", "user created")
            viewModel.saveMoodDetailsToFirestore(mood)
            Log.d("fandabidosi", "user added")
            globalMoodEntry=""
            globalMoodRating=0
            globalKeywordsList.clear()
            globalTriggerWordsList.clear()
            globalNonTriggersList.clear()
            globalPositiveWordsList.clear()

            findNavController().safeNavigate(com.example.elucidate.view.ui.IdentifyPositivesFragmentDirections.actionIdentifyPositivesFragmentToDashboardFragment())
        }


        return binding.root

    }
    fun NavController.safeNavigate(direction: NavDirections) {
        Log.d("navclick", "Click happened")
        currentDestination?.getAction(direction.actionId)?.run {
            Log.d("navclick", "Click Propagated")
            navigate(direction)
        }
    }


}