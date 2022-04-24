package com.example.elucidate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elucidate.view.viewmodel.ViewModel
import com.example.elucidate.dto.User
import com.example.elucidate.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

const val TAG = "FIRESTORE"
private lateinit var auth: FirebaseAuth
lateinit var viewModel: ViewModel
var globalMoodEntry=""
var globalMoodRating=0
var globalKeywordsList= mutableListOf<String>()
var globalTriggerWordsList= mutableListOf<String>()
var globalNonTriggersList= mutableListOf<String>()
var globalPositiveWordsList= mutableListOf<String>()
lateinit var globalUser: User

class MainActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        //val inputStream: InputStream = assets.open("stopwords.txt")
        viewModel= ViewModel()


    }



}
