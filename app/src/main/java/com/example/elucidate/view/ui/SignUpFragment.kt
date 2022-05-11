package com.example.elucidate.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentSignUpBinding
import com.example.elucidate.viewModel


/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */


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

        val email= binding.btnEmail.text
        val password= binding.btnPassword.text
        val name=binding.editTextName.text

        binding.btnCreateAccount.setOnClickListener {

            viewModel.signup("$email", "$password", "$name")
            view?.findNavController()?.navigate(R.id.action_signUpFragment_to_dashboardFragment)

        }

        return binding.root
    }

}


