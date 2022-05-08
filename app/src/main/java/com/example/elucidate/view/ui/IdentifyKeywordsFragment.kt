package com.example.elucidate.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.elucidate.KeywordCreator
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentIdentifyKeywordsBinding
import com.example.elucidate.globalMoodEntryDetails
import com.google.android.material.chip.Chip

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

        val stringForKeywords= globalMoodEntryDetails.moodEntry
        val chosenKeywords= mutableListOf<String>()

        val lower= stringForKeywords.lowercase()
        val fileName= "stopwords.txt"
        val stopWords = mutableListOf<String>()

        activity?.assets?.open(fileName)?.bufferedReader()?.useLines { lines -> lines.forEach { stopWords.add(it)} }

            val keywordCreator= KeywordCreator()
            val printText=keywordCreator.createKeywordList(lower, stopWords)

            for (item in printText){

                val chip=inflater.inflate(R.layout.filter_chip, binding.cgKeywords, false) as Chip
                chip.text=item
                chip.setChipBackgroundColorResource(R.color.white)
                binding.cgKeywords.addView(chip)

            }

            binding.btnCheckWords.setOnClickListener { view: View ->

                val ids = binding.cgKeywords.checkedChipIds

                for (id in ids) {

                    binding.cgKeywords.findViewById<Chip>(id).text.toString()
                    chosenKeywords.add(binding.cgKeywords.findViewById<Chip>(id).text.toString())
                    globalMoodEntryDetails.keywords= chosenKeywords


                    findNavController().safeNavigate(com.example.elucidate.view.ui.IdentifyKeywordsFragmentDirections.actionIdentifyKeywordsFragmentToIdentifyTriggersFragment())

                    }
            }

        return binding.root
    }

    fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run {

            navigate(direction)
        }
    }
}