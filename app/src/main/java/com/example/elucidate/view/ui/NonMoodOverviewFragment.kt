package com.example.elucidate.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentNonMoodOverviewBinding
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
class NonMoodOverviewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentNonMoodOverviewBinding.inflate(layoutInflater)

        binding.btnLogGeneral.setOnClickListener { view: View ->

            view?.findNavController()?.navigate(R.id.action_nonMoodOverviewFragment_to_generalEntryFragment)

        }

        binding.btnGeneralEntries.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_nonMoodOverviewFragment_to_retrieveGeneralEntriesFragment)

        }

        binding.btnPopularGeneralKeywords.setOnClickListener {

            view?.findNavController()?.navigate(R.id.action_nonMoodOverviewFragment_to_generalCloudFragment)

        }



        binding.btnGeneralLogOut.setOnClickListener {

            globalUser.id=""
            globalUser.name=""

            Firebase.auth.signOut()

            view?.findNavController()?.navigate(R.id.action_nonMoodOverviewFragment_to_titleFragment)

        }

        return binding.root
    }


}