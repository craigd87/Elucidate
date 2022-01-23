package com.example.elucidate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elucidate.databinding.ActivitySignUpBinding
import com.example.elucidate.databinding.ActivityTesterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
class tester : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTesterBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth
        val user = auth.currentUser

        if(user!=null){
            binding.textHi.text="Welcome "+user.displayName
        }else{
            binding.textHi.text="user missing!!!!"
        }
    }
}