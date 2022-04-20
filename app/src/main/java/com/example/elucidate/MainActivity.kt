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
