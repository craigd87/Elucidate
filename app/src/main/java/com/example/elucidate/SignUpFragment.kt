package com.example.elucidate

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.elucidate.databinding.FragmentLoginBinding
import com.example.elucidate.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//private lateinit var auth: FirebaseAuth


class SignUpFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSignUpBinding.inflate(layoutInflater)

        // Initialize Firebase Auth
        //auth = Firebase.auth

        val email= binding.btnEmail.text
        val password= binding.btnPassword.text


        binding.btnCreateAccount.setOnClickListener {

            viewModel.signup("$email", "$password")
            viewModel.login("$email", "$password")

            val action = SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment("$email", "$password")
            view?.findNavController()?.navigate(action)
        }

    return binding.root
    }


}