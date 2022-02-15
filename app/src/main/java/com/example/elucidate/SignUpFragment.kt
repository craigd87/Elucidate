package com.example.elucidate

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elucidate.databinding.FragmentLoginBinding
import com.example.elucidate.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var auth: FirebaseAuth

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSignUpBinding.inflate(layoutInflater)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val email= binding.btnEmail.text
        val password= binding.btnPassword.text


        binding.btnCreateAccount.setOnClickListener {

            auth.createUserWithEmailAndPassword ("$email", "$password")
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")

                        //with help from TutorialsEU https://www.youtube.com/watch?v=8I5gCLaS25w

                        val user : FirebaseUser = task.result!!.user!!
                        /*val intent= Intent(this, UpdateProfile::class.java)
                        intent.putExtra("user_id", user.uid)
                        intent.putExtra("email", "$email")
                        intent.putExtra("password", "$password")
                        startActivity(intent)*/

                    }

                }

        }

        /*binding.btnCheckName.setOnClickListener{
            if (user != null) {
                Toast.makeText(baseContext, "welcome "+user.displayName,
                    Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(baseContext, "user missing",
                    Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnSignOut.setOnClickListener{
            Firebase.auth.signOut()
        }*/





    return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}