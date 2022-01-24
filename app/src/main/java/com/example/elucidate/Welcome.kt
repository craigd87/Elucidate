package com.example.elucidate

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.elucidate.databinding.ActivitySignUpBinding
import com.example.elucidate.databinding.ActivityWelcomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWelcomeBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding?.root)

        auth = Firebase.auth



        /*val email=intent.getStringExtra("Email")
        val password=intent.getStringExtra("Password")
        var name=intent.getStringExtra("Name")*/
        val intent= intent
        val userDetails = intent.extras
        val email= userDetails?.getString("Email")
        val password= userDetails?.getString("Password")
        var name= userDetails?.getString("Name")

        auth.signInWithEmailAndPassword("$email", "$password")

            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        val user = auth.currentUser

        val profileUpdates =
            UserProfileChangeRequest.Builder()
                .setDisplayName("$name")
                .build()

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "User profile updated.")

                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }

            }
        name= user.displayName
        val welcomeMessage= "Welcome $name"
        binding.textViewWelcome.text=welcomeMessage

        binding.btnGoDashboard.setOnClickListener{
            val intent= Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        binding.btnWelcomeLogOut.setOnClickListener{
            Firebase.auth.signOut()

        }
            }
}