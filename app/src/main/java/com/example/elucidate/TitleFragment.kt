package com.example.elucidate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.elucidate.databinding.FragmentDashboardBinding
import com.example.elucidate.databinding.FragmentTitleBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth


class TitleFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTitleBinding.inflate(layoutInflater)

        auth = Firebase.auth
        val user= auth.currentUser

        binding.btnGetStarted.setOnClickListener {
            if (user!=null){
                view?.findNavController()?.navigate(R.id.action_titleFragment_to_dashboardFragment)
                //view?.findNavController()?.navigate(R.id.action_titleFragment_to_testViewModelFragment)
            }else{
                view?.findNavController()?.navigate(R.id.action_titleFragment_to_loginFragment2)
            //view?.findNavController()?.navigate(R.id.action_titleFragment_to_chipTestFragment)
            }
        }

        binding.btnChart.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_titleFragment_to_chartFragment)
        }

        return binding.root
    }


}