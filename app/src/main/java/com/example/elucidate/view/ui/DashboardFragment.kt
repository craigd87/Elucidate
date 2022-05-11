package com.example.elucidate.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentDashboardBinding
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

/**
 * A [Fragment] to show the main dashboard.
 */
class DashboardFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDashboardBinding.inflate(layoutInflater)


            binding.ivMood.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_moodOverviewFragment)
            }

            binding.ivGeneral.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_nonMoodOverviewFragment)
            }

        return binding.root
    }

}