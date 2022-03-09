package com.example.elucidate

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.elucidate.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.InputStream

//const val TAG = "FIRESTORE"
private lateinit var auth: FirebaseAuth

class MainActivity() : AppCompatActivity() {
    /*private var binding: ActivityMainBinding? = null

    constructor(parcel: Parcel) : this() {

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        val inputStream: InputStream = assets.open("stopwords.txt")

        /*auth = Firebase.auth
        val currentUser = auth.currentUser*/


        /*if(currentUser != null){
            /*val intent= Intent(this, Dashboard::class.java)
            startActivity(intent)*/
        }else{

            /*val intent= Intent(this, Login::class.java)
            startActivity(intent)*/
        }*/

        //uploadData()

        /* useful
        logIn(binding)
        signUp(binding)
        dashboard(binding)
        */

    }

    /*fun login (binding: ActivityMainBinding){
        binding.btnSignUp.setOnClickListener{
            val intent= Intent(this, Login::class.java)
            startActivity(intent)
        }
    }*/

    /* useful
    fun logIn (binding: ActivityMainBinding){
        binding.btnLogIn.setOnClickListener{
            val intent= Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
    fun signUp (binding: ActivityMainBinding){
        binding.btnSignUp.setOnClickListener{
            val intent= Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
    fun dashboard (binding: ActivityMainBinding){
        binding.btnDashboard.setOnClickListener{
            val intent= Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }*/

    /*private fun uploadData() {
        binding!!.btnUploadData.setOnClickListener {

            // create a dummy data
            val hashMap = hashMapOf<String, Any>(
                "name" to "John doe",
                "city" to "Nairobi",
                "age" to 24
            )

            // use the add() method to create a document inside users collection
            FirebaseUtils().fireStoreDatabase.collection("users")
                .add(hashMap)
                .addOnSuccessListener {
                    Log.d(TAG, "Added document with ID ${it.id}")
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error adding document $exception")
                }
        }
    }*/

}
