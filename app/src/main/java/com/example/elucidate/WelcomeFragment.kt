package com.example.elucidate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.elucidate.databinding.FragmentDashboardBinding
import com.example.elucidate.databinding.FragmentWelcomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//private lateinit var auth: FirebaseAuth

class WelcomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val args: UpdateProfileFragmentArgs by navArgs<UpdateProfileFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //auth = Firebase.auth
        val binding = FragmentWelcomeBinding.inflate(layoutInflater)

        val email= args.email.toString()
        val password= args.password.toString()
        binding.btnLog.setOnClickListener{
            //viewModel.loginAfterSignup("$email", "$password")
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToUpdateProfileFragment("$email", "$password")
            view?.findNavController()?.navigate(action)
        }

        return binding.root
    }


}