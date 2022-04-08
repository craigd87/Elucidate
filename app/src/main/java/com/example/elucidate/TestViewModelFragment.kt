package com.example.elucidate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elucidate.databinding.ActivityMainBinding.inflate
import com.example.elucidate.databinding.FragmentTestViewModelBinding
import com.example.elucidate.databinding.FragmentUpdateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
class TestViewModelFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =FragmentTestViewModelBinding.inflate(layoutInflater)
        /*auth= Firebase.auth
        val userAuth= auth.currentUser
        val uid= userAuth?.uid*/


        binding.vMTestButton.setOnClickListener{
            val name= binding.vMTestName.text.toString()
            val age= binding.vMTestAge.text.toString()
            val user= User("$name","$age")
            viewModel.saveUserDetailsToFirestore(user)
        }

        return binding.root
    }


}