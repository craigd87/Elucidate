package com.example.elucidate

import android.app.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.elucidate.databinding.FragmentDashboardBinding
import com.example.elucidate.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth

class LoginFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(layoutInflater)

        auth= Firebase.auth
        val email = binding.loginEmailAddress.text
        val password = binding.loginPassword.text

            binding.btnLogin.setOnClickListener {
            //login("$email", "$password")
            /*view?.let { it1 -> FirebaseUtils().login("$email", "$password", it1) }
                   }*/var retrievedUser= mutableListOf<User>()
                    //val task=viewModel.login("$email", "$password")
                //Log.d("Belfast", id)
                auth.signInWithEmailAndPassword("$email", "$password")
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithEmail:success")
                            //view?.findNavController()?.navigate(R.id.action_loginFragment2_to_dashboardFragment)
                            val user: FirebaseUser = task.result!!.user!!
                            val userId = user.uid
                            //idList.add(userId)
                            var query=viewModel.retrieveUser(userId)
                            query.addOnCompleteListener() { task->
                                if(task.isSuccessful){
                                    val documents=query.result
                                    Log.d("Marnie", "$documents")
                                    if (documents != null) {
                                        for (document in documents){
                                            Log.d("exist", "DocumentSnapshot data: ${document.data}")
                                            val retrievedUser = document.toObject(User::class.java)
                                            globalUser = retrievedUser
                                            Log.d("Hannah", "$globalUser")
                                        }
                                    }else{
                                        //if (document != null) {
                                        //Log.d("exist", "DocumentSnapshot data: ${document.data}")
                                        //document.
                                        //moodEntry = document.getString("moodRating").toString()
                                        //val retrievedUser = document.toObject(User::class.java)
                                        //retrievedUsers.add(retrievedUser)


                                        //}
                                        Log.d("pain", "null")
                                    }

                                }
                            }

                                /*.observe(viewLifecycleOwner, Observer { it ->
                                    Log.d("garfield", "$it")
                                    retrievedUser = it as MutableList<User>
                                    Log.d("retrieved mood", "$retrievedUser")*/
                                    //var user: Mood

                            /*for (item in retrievedUser) {
                                        globalUser = item
                                        Log.d("Hannah", "$globalUser")
                                    }*/

                                }


                    }
                            view?.findNavController()?.navigate(R.id.action_loginFragment2_to_dashboardFragment)
            }


            binding.btnSignUpLink.setOnClickListener{
                view?.findNavController()?.navigate(R.id.action_loginFragment2_to_signUpFragment)
            }

        return binding.root
    }

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword("$email", "$password")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    view?.findNavController()?.navigate(R.id.action_loginFragment2_to_dashboardFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(context,"Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }




}


