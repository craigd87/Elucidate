package com.example.elucidate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.elucidate.databinding.FragmentIdentifyTriggersBinding
import com.google.android.material.chip.Chip


class IdentifyTriggersFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentIdentifyTriggersBinding.inflate(layoutInflater)

        val chosenTriggers= mutableListOf<String>()

        //val keywordsList= viewModel.keywordsList.value

        for (item in globalKeywordsList){

            Log.d("triggerChips", item)
            //val chip= Chip(activity)
            val chip=inflater.inflate(R.layout.filter_chip, binding.cgTriggers, false) as Chip
            chip.text=item


            chip.setChipBackgroundColorResource(R.color.white)
            //chip.setCloseIconVisible(true);
            binding.cgTriggers.addView(chip)
        }

        binding.btnNext.setOnClickListener { view: View ->
            val ids = binding.cgTriggers.checkedChipIds

            for (id in ids) {
                val wordSelection =
                    binding.cgTriggers.findViewById<Chip>(id).text.toString()
                //chosenTriggers.add(binding.cgTriggers.findViewById<Chip>(id).text.toString())
                //chosenTriggers.add(wordSelection)
                globalTriggerWordsList.add(wordSelection)


                Log.d("Mtriggers", globalTriggerWordsList.toString())


                //val action=IdentifyKeywordsFragmentDirections.actionIdentifyKeywordsFragmentToKeywordQualityFragment(chosenKeywords)
            }
            for (item in globalKeywordsList){
                if(item !in globalTriggerWordsList){
                    globalNonTriggersList.add(item)
                }
            }
            findNavController().safeNavigate(IdentifyTriggersFragmentDirections.actionIdentifyTriggersFragmentToIdentifyPositivesFragment())
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