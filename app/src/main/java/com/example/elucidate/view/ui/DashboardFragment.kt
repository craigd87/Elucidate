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


class DashboardFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDashboardBinding.inflate(layoutInflater)

        /*DO NOT DELETE YET
        val queryRef=FirebaseUtils().printName("$id")
            queryRef.addOnSuccessListener { documents ->
            for (document in documents) {
                Log.d("exist", "DocumentSnapshot data: ${document.data}")
                val name = document.getString("name").toString()
                binding.tvDashWelcome.text = "Hi " + name+"!"

            }

        }*/


            binding.btnLogMood.setOnClickListener { view: View ->

                view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_moodEntryFragment)

            }

            binding.btnInsights.setOnClickListener {

                view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_retreiveMoodEntriesFragment)

            }

            binding.btnPopularKeywords.setOnClickListener {

                view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_cloudFragment)

            }

            binding.btnPopularTriggers.setOnClickListener {

                view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_triggerCloudFragment)

            }

            binding.btnPopularPositives.setOnClickListener {

                view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_positiveCloudFragment)

            }

            binding.btnMoodRatingGraph.setOnClickListener{

                view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_chartFragment)

            }

            binding.btnDashLogOut.setOnClickListener {

                globalUser.id=""
                globalUser.name=""

                Firebase.auth.signOut()

                view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_titleFragment)

            }

        return binding.root
    }

}