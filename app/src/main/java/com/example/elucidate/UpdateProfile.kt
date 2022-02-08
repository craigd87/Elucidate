package com.example.elucidate

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.elucidate.databinding.ActivityUpdateProfileBinding
import com.example.elucidate.databinding.ActivityWelcomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
class UpdateProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateProfileBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        auth = Firebase.auth
        val user = auth.currentUser
        val userId= intent.getStringExtra("user_id").toString()
        val email=intent.getStringExtra("email").toString()
        val password=intent.getStringExtra("password").toString()
        val name= binding.editTexProfileName.text
        val age= binding.editTextProfileAge.text


        //binding.textViewEnterDetails.text="User id : "+userId
        val uDetails = hashMapOf<String, Any>()

        login(email, password, "$name")

        binding.btnUpdateProfile.setOnClickListener{

            if (user != null) {
                updateProfile("$name", "$age", user, userId, uDetails)
            }
        }

    }

    fun login(email: String, password: String, name: String){
        FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser
            if (user!=null){
                Toast.makeText(this, "Logged in",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                auth.signInWithEmailAndPassword("$email", "$password")

                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(com.example.elucidate.TAG, "signInWithEmail:success")

                            //val user = auth.currentUser
                            Toast.makeText(
                                baseContext, "Success! welcome $name!",
                                Toast.LENGTH_SHORT
                            ).show()


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(com.example.elucidate.TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
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
                        baseContext, "profile update failed.",
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
                    baseContext, "SUCCESS!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error adding document $exception")
                Toast.makeText(
                    baseContext, "Error adding document $exception",
                    Toast.LENGTH_LONG
                ).show()
            }
        val intent= Intent(this, Dashboard::class.java)
        startActivity(intent)
    }
    public override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(FirebaseAuth.AuthStateListener {  })
    }

    public override fun onPause() {
        super.onPause()
        auth.removeAuthStateListener(FirebaseAuth.AuthStateListener {  })
    }
}