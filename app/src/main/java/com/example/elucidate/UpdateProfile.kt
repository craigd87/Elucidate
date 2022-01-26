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
        val name= binding.editTexProfileName.text
        val age= binding.editTextProfileAge.text
        val uid= user?.uid
        val hashMap = hashMapOf<String, Any>(
            "id" to "$uid",
            "name" to "$name",
            "age" to "$age"

        )

        binding.btnUpdateProfile.setOnClickListener{


            /*val profileUpdates =
                UserProfileChangeRequest.Builder()
                    .setDisplayName("$name")
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

                }*/
            FirebaseUtils().fireStoreDatabase.collection("users")
                .add(hashMap)
                .addOnSuccessListener {
                    Log.d(TAG, "Added document with ID ${it.id}")
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
    }
}