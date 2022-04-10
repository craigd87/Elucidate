package com.example.elucidate

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import com.example.elucidate.databinding.FragmentLoginBinding
import com.example.elucidate.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth
private lateinit var mFirebaseAuth:FirebaseAuth
private var mFirebaseAuthLis:FirebaseAuth.AuthStateListener?= null


class SignUpFragment : Fragment() {

    /*private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->

        var currentUser = firebaseAuth.currentUser
    }*/


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
        auth = Firebase.auth
        mFirebaseAuth= FirebaseAuth.getInstance()
        val currentUser=auth.currentUser

        val name= binding.editTextName.text
        val email= binding.btnEmail.text
        val password= binding.btnPassword.text
        val age=30


        binding.btnCreateAccount.setOnClickListener {


            viewModel.signup("$email", "$password")
            viewModel.login("$email", "$password")
            mFirebaseAuthLis=FirebaseAuth.AuthStateListener { firebaseAuth ->

                var currentUser = firebaseAuth.currentUser
                if (currentUser != null) {
                    Log.d("Hatter", "woohoo!")
                    //viewModel.login("$email", "$password")
                    val id = currentUser?.uid
                    val user = User("$id", "$name", "$age")
                    viewModel.saveUserDetailsToFirestore(user)

                    /*val action = SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment("$email", "$password")
                view?.findNavController()?.navigate(action)*/
                    view?.findNavController()
                        ?.navigate(R.id.action_signUpFragment_to_dashboardFragment)

                } else {
                    Log.d("Alice", "nothing found")
                }

            }
        }
        

    return binding.root
    }

    override fun onStart() {
        super.onStart()
        mFirebaseAuthLis?.let { mFirebaseAuth.addAuthStateListener(it) }
        }

    override fun onStop() {
        super.onStop()
        mFirebaseAuthLis?.let { mFirebaseAuth.removeAuthStateListener(it) }

        }

    /*override fun onAuthStateChanged(@NonNull auth: FirebaseAuth){

        //val action = SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment("$email", "$password")
        //view?.findNavController()?.navigate(action)
        //view?.findNavController()?.navigate(R.id.action_signUpFragment_to_updateProfileFragment)
    }*/

}

