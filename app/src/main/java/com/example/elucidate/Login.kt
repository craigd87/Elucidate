package com.example.elucidate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.elucidate.databinding.ActivityLoginBinding
import com.example.elucidate.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth= Firebase.auth
        val email = binding.loginEmailAddress.text
        val password = binding.loginPassword.text

        binding.btnLogin.setOnClickListener{
            login("$email", "$password")
        }

        binding.btnSignUpLink.setOnClickListener{
            signUp()
        }
    }

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword("$email", "$password")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    /*val intent= Intent(this, Dashboard::class.java)
                    startActivity(intent)*/
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }

    fun signUp(){
        val intent= Intent(this, SignUp::class.java)
        startActivity(intent)
    }
}