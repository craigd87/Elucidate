package com.example.elucidate

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
        setContentView(binding?.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val email= binding.btnEmail.text.toString()
        val password= binding.btnPassword.text.toString()


        binding.btnCreateAccount.setOnClickListener {

            auth.createUserWithEmailAndPassword (email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")

                        //with help from TutorialsEU https://www.youtube.com/watch?v=8I5gCLaS25w

                        val user : FirebaseUser = task.result!!.user!!
                        val intent= Intent(this, UpdateProfile::class.java)
                        intent.putExtra("user_id", user.uid)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        startActivity(intent)

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


