package com.example.elucidate.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.elucidate.*
import com.example.elucidate.databinding.FragmentIdentifyGeneralKeywordsBinding
import com.google.android.material.chip.Chip
import com.google.firebase.Timestamp
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [IdentifyGeneralKeywordsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IdentifyGeneralKeywordsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentIdentifyGeneralKeywordsBinding.inflate(layoutInflater)

        val stringForKeywords= globalGeneralEntryDetails.textEntry
        val chosenKeywords= mutableListOf<String>()

        val lower= stringForKeywords.lowercase()
        val fileName= "stopwords.txt"
        val stopWords = mutableListOf<String>()

        activity?.assets?.open(fileName)?.bufferedReader()?.useLines { lines -> lines.forEach { stopWords.add(it)} }

        val keywordCreator= KeywordCreator()
        val printText=keywordCreator.createKeywordList(lower, stopWords)

        for (item in printText){

            val chip=inflater.inflate(R.layout.filter_chip, binding.cgGeneralKeywords, false) as Chip
            chip.text=item
            chip.setChipBackgroundColorResource(R.color.white)
            binding.cgGeneralKeywords.addView(chip)

        }

        binding.btnSubmitWords.setOnClickListener { view: View ->

            val ids = binding.cgGeneralKeywords.checkedChipIds

            for (id in ids) {

                binding.cgGeneralKeywords.findViewById<Chip>(id).text.toString()
                chosenKeywords.add(binding.cgGeneralKeywords.findViewById<Chip>(id).text.toString())
                globalGeneralEntryDetails.keywords= chosenKeywords

            }
            val id= globalUser.id
            val time= Timestamp(Date())
            globalGeneralEntryDetails.id=id
            globalGeneralEntryDetails.time=time
            viewModel.saveGeneralEntryDetailsToFirestore(globalGeneralEntryDetails)
            findNavController().safeNavigate(com.example.elucidate.view.ui.IdentifyGeneralKeywordsFragmentDirections.actionIdentifyGeneralKeywordsFragmentToDashboardFragment())
        }

        return binding.root
    }

    fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run {

            navigate(direction)
        }
    }


}