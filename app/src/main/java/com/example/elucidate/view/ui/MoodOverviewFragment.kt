package com.example.elucidate.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentMoodOverviewBinding
import com.example.elucidate.globalUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */

class MoodOverviewFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentMoodOverviewBinding.inflate(layoutInflater)

        binding.btnLogMood.setOnClickListener { view: View ->

            view?.findNavController()?.navigate(R.id.action_moodOverviewFragment_to_moodEntryFragment)

        }

        binding.btnMoodEntries.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_moodOverviewFragment_to_retreiveMoodEntriesFragment)

        }

        binding.btnPopularMoodKeywords.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_moodOverviewFragment_to_cloudFragment)

        }

        binding.btnPopularTriggers.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_moodOverviewFragment_to_triggerCloudFragment)

        }

        binding.btnPopularPositives.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_moodOverviewFragment_to_positiveCloudFragment)

        }

        binding.btnMoodRatingGraph.setOnClickListener{

            view?.findNavController()?.navigate(R.id.action_moodOverviewFragment_to_chartFragment)

        }

        binding.btnMoodLogOut.setOnClickListener {

            globalUser.id=""
            globalUser.name=""

            Firebase.auth.signOut()

            view?.findNavController()?.navigate(R.id.action_moodOverviewFragment_to_titleFragment)

        }

        return binding.root
    }


}