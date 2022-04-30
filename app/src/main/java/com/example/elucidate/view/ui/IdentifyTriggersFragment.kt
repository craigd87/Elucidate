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


        for (item in globalMoodEntryDetails.keywords){

            Log.d("triggerChips", item)
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
                chosenTriggers.add(wordSelection)

                globalMoodEntryDetails.triggers=chosenTriggers


            }


            for (item in globalMoodEntryDetails.keywords){

                if(item !in globalMoodEntryDetails.triggers){
                    globalNonTriggersList.add(item)
                }
            }
            findNavController().safeNavigate(com.example.elucidate.view.ui.IdentifyTriggersFragmentDirections.actionIdentifyTriggersFragmentToIdentifyPositivesFragment())
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