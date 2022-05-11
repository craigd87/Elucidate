package com.example.elucidate.view.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.elucidate.*
import com.example.elucidate.databinding.FragmentMoodEntryBinding
import com.example.elucidate.model.FirebaseUtils
import com.google.firebase.Timestamp
import java.util.*
import kotlin.collections.HashMap


/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */
class MoodEntryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMoodEntryBinding.inflate(layoutInflater)

        binding.tvEnterMood.text = "Hi " + globalUser.name+"! please enter details about how you are feeling"

        binding.btnSubmitMood.setOnClickListener { view: View ->

            globalMoodEntryDetails.moodEntry=binding.etEnterMood.text.toString()
            globalMoodEntryDetails.moodRating=binding.sbRateMood.progress.toString()


            view.findNavController().navigate(R.id.action_moodEntryFragment_to_identifyKeywordsFragment)

        }

        return binding.root
    }


}