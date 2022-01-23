package com.example.elucidate

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.elucidate.databinding.ActivityMainBinding
import com.example.elucidate.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
const val TAG = "FIRESTORE"
private lateinit var auth: FirebaseAuth
class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding?.root)

        // ...
        // Initialize Firebase Auth
        auth = Firebase.auth

        val name= binding.editTextName.text
        val email= binding.btnEmail.text
        val password= binding.btnPassword.text


        /*fun logIn (binding: ActivitySignUpBinding){
            binding.btnCreateAccount.setOnClickListener{
                val intent= Intent(this, AccountCreated::class.java)
                startActivity(intent)
            }*/
        binding.btnCreateAccount.setOnClickListener {
            auth.createUserWithEmailAndPassword("$email", "$password")
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(
                            baseContext, "SUCCESS!",
                            Toast.LENGTH_SHORT
                        ).show()

                    }



                }
            val intent= Intent(this, Welcome::class.java).apply{
                intent.putExtra("Name","$name")
                intent.putExtra("Email","$email")
                intent.putExtra("Password","$password")
            }

            startActivity(intent)
        }

        val user = auth.currentUser

        binding.btnCheckName.setOnClickListener{
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
        }


    }

    /*private fun updateUI(user: FirebaseUser?) {

    }*/
}