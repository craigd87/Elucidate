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
import com.example.elucidate.*
import com.example.elucidate.databinding.FragmentIdentifyPositivesBinding
import com.google.android.material.chip.Chip
import com.google.firebase.Timestamp
import java.util.*

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */
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

            val chip=inflater.inflate(R.layout.filter_chip, binding.cgPositives, false) as Chip
            chip.text=item
            chip.setChipBackgroundColorResource(R.color.white)
            binding.cgPositives.addView(chip)
        }

        binding.btnComplete.setOnClickListener { view: View ->

            val ids = binding.cgPositives.checkedChipIds

            for (id in ids) {

                val wordSelection = binding.cgPositives.findViewById<Chip>(id).text.toString()
                chosenPositives.add(wordSelection)
                globalMoodEntryDetails.positives=chosenPositives

            }

            val id= globalUser.id
            val time= Timestamp(Date())
            globalMoodEntryDetails.id=id
            globalMoodEntryDetails.time=time
            viewModel.saveMoodDetailsToFirestore(globalMoodEntryDetails)
            globalNonTriggersList.clear()

            findNavController().safeNavigate(com.example.elucidate.view.ui.IdentifyPositivesFragmentDirections.actionIdentifyPositivesFragmentToDashboardFragment())
        }

        return binding.root

    }

    fun NavController.safeNavigate(direction: NavDirections) {

        currentDestination?.getAction(direction.actionId)?.run {

            navigate(direction)
        }
    }


}