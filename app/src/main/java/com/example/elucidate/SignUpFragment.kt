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
        //viewModel.initializeAuth(auth)

        val email= binding.btnEmail.text
        val password= binding.btnPassword.text
        val name=binding.editTextName.text
        val age=242

        //auth.currentUser


        binding.btnCreateAccount.setOnClickListener {

           //signin("$email", "$password", "$name", "$age")
            viewModel.signup("$email", "$password", "$name", "$age")
            view?.findNavController()?.navigate(R.id.action_signUpFragment_to_dashboardFragment)

        }

        return binding.root
    }

    /*fun signin(email:String, password:String, name:String, age:String){
        auth.createUserWithEmailAndPassword ("$email", "$password")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")

                    //with help from TutorialsEU https://www.youtube.com/watch?v=8I5gCLaS25w

                    val user : FirebaseUser = task.result!!.user!!
                    val userId=user.uid

                    val user1=User("$userId", "$name", "$age")
                    viewModel.saveUserDetailsToFirestore(user1)

                    /*val action = SignUpFragmentDirections.actionSignUpFragmentToUpdateProfileFragment( "$email","$password", "$userId")
                    //val action2= SignUpFragmentDirections.actionSignUpFragmentToUpdateProfileFragment()
                    view?.findNavController()?.navigate(action)*/

                    view?.findNavController()?.navigate(R.id.action_signUpFragment_to_dashboardFragment)
                }

            }
    }*/


}

/*package com.example.elucidate

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
//private lateinit var mFirebaseAuth:FirebaseAuth
//private var mFirebaseAuthLis:FirebaseAuth.AuthStateListener?= null


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
        //mFirebaseAuth= FirebaseAuth.getInstance()
        val currentUser=auth.currentUser

        val name= binding.editTextName.text
        val email= binding.btnEmail.text
        val password= binding.btnPassword.text
        val age=30


        binding.btnCreateAccount.setOnClickListener {

            signup("$email", "$password")
            //viewModel.signup("$email", "$password", "$name","$age" )
            //viewModel.loginAfterSignup("$email", "$password")
            //viewModel.login("$email", "$password")
            /*mFirebaseAuthLis=FirebaseAuth.AuthStateListener { firebaseAuth ->

                var currentUser = firebaseAuth.currentUser*/
                if (currentUser != null) {
                    Log.d("Hatter", "woohoo!")
                    //viewModel.login("$email", "$password")
                   /* val id = currentUser?.uid
                    val user = User("$id", "$name", "$age")
                    viewModel.saveUserDetailsToFirestore(user)*/

                    /*val action = SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment("$email", "$password")
                view?.findNavController()?.navigate(action)*/
                    view?.findNavController()
                        ?.navigate(R.id.action_signUpFragment_to_dashboardFragment)

                } else {
                    Log.d("Alice", "nothing found")
                }

            }

        

    return binding.root
    }

    /*override fun onStart() {
        super.onStart()
        mFirebaseAuthLis?.let { mFirebaseAuth.addAuthStateListener(it) }
        }

    override fun onStop() {
        super.onStop()
        mFirebaseAuthLis?.let { mFirebaseAuth.removeAuthStateListener(it) }

        }*/

    /*override fun onAuthStateChanged(@NonNull auth: FirebaseAuth){

        //val action = SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment("$email", "$password")
        //view?.findNavController()?.navigate(action)
        //view?.findNavController()?.navigate(R.id.action_signUpFragment_to_updateProfileFragment)
    }*/
    fun signup(email: String, password: String) {
        auth.createUserWithEmailAndPassword ("$email", "$password")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")



                    //with help from TutorialsEU https://www.youtube.com/watch?v=8I5gCLaS25w

                    val currentUser: FirebaseUser = task.result!!.user!!
                    /*val id = currentUser.uid
                    val user = User("$id", "$name", "$age")*/
                    //viewModel.saveUserDetailsToFirestore(user)



                    val action =
                        SignUpFragmentDirections.actionSignUpFragmentToUpdateProfileFragment(
                            //user.uid,
                            "$email",
                            "$password"
                        )
                    view?.findNavController()?.navigate(action)
                }

            }
        //return user
    }

}
*/
