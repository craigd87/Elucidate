package com.example.elucidate.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentDashboardBinding
import com.example.elucidate.databinding.FragmentTitleBinding
import com.example.elucidate.dto.User
import com.example.elucidate.globalUser
import com.example.elucidate.viewModel
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
        val id = user?.uid

        binding.btnGetStarted.setOnClickListener {
            if (user!=null){

                //globalUser.id=user.uid
                //Log.d("Jessa", "$globalUser")
                val getName= viewModel.getCurrentUserName("$id")
                getName.addOnSuccessListener { documents ->
                    for (document in documents) {
                        //Log.d("exist", "DocumentSnapshot data: ${document.data}")
                        val name = document.getString("name").toString()
                        globalUser= User("$id", name)
                        Log.d("Manie", "$globalUser")
                    }
                }

                //globalUser.name=name



                view?.findNavController()?.navigate(R.id.action_titleFragment_to_dashboardFragment)
                //view?.findNavController()?.navigate(R.id.action_titleFragment_to_testViewModelFragment)

            }else{
                view?.findNavController()?.navigate(R.id.action_titleFragment_to_loginFragment2)
            //view?.findNavController()?.navigate(R.id.action_titleFragment_to_chipTestFragment)
            }
        }

        binding.btnChart.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_titleFragment_to_chartFragment)
            //view?.findNavController()?.navigate(R.id.action_titleFragment_to_cloudFragment)
        }

        return binding.root
    }


}