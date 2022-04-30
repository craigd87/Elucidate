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
import com.example.elucidate.databinding.FragmentIdentifyKeywordsBinding
import com.example.elucidate.model.FirebaseUtils
import com.google.android.material.chip.Chip
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


        val stringForKeywords= globalMoodEntryDetails.moodEntry
        val chosenKeywords= mutableListOf<String>()

        val lower= stringForKeywords.lowercase()
                        Log.d("lowwwwer", lower)

                        val fileName= "stopwords.txt"
                        val stopWords = mutableListOf<String>()
                        val inputString = activity?.assets?.open(fileName)?.bufferedReader()?.useLines { lines -> lines.forEach { stopWords.add(it)} }
                        stopWords.forEach{
                            Log.i("Craig",  it)
                        }

                        val keywordCreator= KeywordCreator()
                        val printText=keywordCreator.createKeywordList(lower, stopWords)

                        for (item in printText){
                            Log.d("chips", item)

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
                                chosenKeywords.add(binding.cgKeywords.findViewById<Chip>(id).text.toString())
                                globalMoodEntryDetails.keywords= chosenKeywords


                                Log.d("favourites", wordSelection)

                                findNavController().safeNavigate(com.example.elucidate.view.ui.IdentifyKeywordsFragmentDirections.actionIdentifyKeywordsFragmentToIdentifyTriggersFragment())

                            }
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