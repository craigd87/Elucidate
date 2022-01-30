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
        //setContentView(R.layout.activity_main)
        setContentView(binding?.root)

        // ...
        // Initialize Firebase Auth
        auth = Firebase.auth


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

                        //with help from TutorialsEU https://www.youtube.com/watch?v=8I5gCLaS25w

                        val user : FirebaseUser = task.result!!.user!!
                        val intent= Intent(this, UpdateProfile::class.java)
                        intent.putExtra("user_id", user.uid)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        startActivity(intent)
                        /*Toast.makeText(
                            baseContext, "Welcome $name, email: $email, password: $password",
                            Toast.LENGTH_LONG
                        ).show()*/

                    }



                }

            /*auth.signInWithEmailAndPassword("$email", "$password")

                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(com.example.elucidate.TAG, "signInWithEmail:success")

                        //val user = auth.currentUser
                        /*Toast.makeText(
                            baseContext, "Success! welcome $name!",
                            Toast.LENGTH_SHORT
                        ).show()*/

                        /*auth.addAuthStateListener(FirebaseAuth.AuthStateListener{auth -> user
                            
                            if(user!=null){
                                val intent= Intent(this, UpdateProfile::class.java)
                                startActivity(intent)
                            }else{

                                Toast.makeText(
                                    baseContext, "No user",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        })*/



                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(com.example.elucidate.TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }*/

            /*val handler= Handler(Looper.getMainLooper())
            handler.postDelayed({
                if(user!=null){
                    val intent= Intent(this, UpdateProfile::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(
                        baseContext, "No user",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }, 5000)*/

            /*val intent= Intent(this, Welcome::class.java).apply{
                intent.putExtra("Name","$name")
                intent.putExtra("Email","$email")
                intent.putExtra("Password","$password")
            }*/

            /*val intent= Intent(this, Welcome::class.java)
            val userDetails= Bundle()
            userDetails.putString("Name","$name")
            userDetails.putString("Email","$email")
            userDetails.putString("Password","$password")
            intent.putExtras(userDetails)

            startActivity(intent)*/

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

    /*private fun updateUI(user: FirebaseUser?) {

    }*/
    public override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(FirebaseAuth.AuthStateListener {  })
    }

    public override fun onPause() {
        super.onPause()
        auth.removeAuthStateListener(FirebaseAuth.AuthStateListener {  })
    }
}


