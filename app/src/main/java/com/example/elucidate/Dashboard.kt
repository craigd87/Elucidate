package com.example.elucidate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elucidate.databinding.ActivityDashboardBinding
import com.example.elucidate.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDashboardBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        auth= Firebase.auth
        /*binding.btnLogMood.setOnClickListener{
            logMood(binding)

        }*/



    }


    /*fun logMood(binding: ActivityDashboardBinding){
        val editText= binding.editTextLogMood
        val moodTextView= binding.textViewMoodLog
        moodTextView.text=editText.text

    }*/
}