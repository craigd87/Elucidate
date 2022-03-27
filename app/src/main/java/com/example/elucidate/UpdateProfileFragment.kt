package com.example.elucidate

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle

import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.elucidate.databinding.FragmentLoginBinding
import com.example.elucidate.databinding.FragmentUpdateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth


class UpdateProfileFragment : Fragment() {

    private val args: UpdateProfileFragmentArgs by navArgs<UpdateProfileFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentUpdateProfileBinding.inflate(layoutInflater)

        auth = Firebase.auth
        val user = auth.currentUser
        val userId= args.userId.toString()
        val email= args.email.toString()
        val password= args.password.toString()
        val name= binding.editTexProfileName.text
        val age= binding.editTextProfileAge.text
        val uDetails = hashMapOf<String, Any>()

        login(email, password, "$name")

        binding.btnUpdateProfile.setOnClickListener{

            if (user != null) {
                updateProfile("$name", "$age", user, userId, uDetails)
            }
        }

        return binding.root
    }

    fun login(email: String, password: String, name: String){
        FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser
            if (user!=null){
                Toast.makeText(context, "Logged in",
                    Toast.LENGTH_SHORT
                ).show()

            }else{
                auth.signInWithEmailAndPassword("$email", "$password")
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            Toast.makeText(
                                context, "Success! welcome $name!",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                context, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
            }
        }
    }

    fun updateProfile(name: String, age: String, user: FirebaseUser, userId: String, uDetails: HashMap<String, Any> ){
        val profileUpdates =
            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "User profile updated.")

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "profile update failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        uDetails.put("id", userId)
        uDetails.put("name", "$name")
        uDetails.put("age", "$age")
        uDetails.put("working?", "yes this seems to be working!")

        val users = FirebaseUtils().fireStoreDatabase.collection("users")

        users.document(userId).set(uDetails)
            .addOnSuccessListener {
                //Log.d(TAG, "Added document with ID ${it.id}")
                Toast.makeText(
                    context, "SUCCESS!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error adding document $exception")
                Toast.makeText(
                    context, "Error adding document $exception",
                    Toast.LENGTH_LONG
                ).show()
            }

        view?.findNavController()?.navigate(R.id.action_updateProfileFragment_to_dashboardFragment)
    }


}