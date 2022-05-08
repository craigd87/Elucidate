package com.example.elucidate.view.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.elucidate.R
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
        //(requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val binding = FragmentTitleBinding.inflate(layoutInflater)

        auth = Firebase.auth
        val user= auth.currentUser
        //val id = user?.uid
        //val user=viewModel.getCurrentUser()
        val id= user?.uid

        Handler(Looper.getMainLooper()).postDelayed({

            if (user!=null){

                val getName= viewModel.getCurrentUserName("$id")
                getName.addOnSuccessListener { documents ->

                    for (document in documents) {

                        val name = document.getString("name").toString()
                        globalUser= User("$id", name)

                    }
                }

                view?.findNavController()?.navigate(R.id.action_titleFragment_to_dashboardFragment)

            }else{

                view?.findNavController()?.navigate(R.id.action_titleFragment_to_loginFragment2)

            }
        },3000)


        return binding.root
    }


}