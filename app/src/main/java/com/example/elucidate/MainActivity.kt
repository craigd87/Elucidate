package com.example.elucidate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elucidate.view.viewmodel.ViewModel
import com.example.elucidate.dto.User
import com.example.elucidate.databinding.ActivityMainBinding
import com.example.elucidate.dto.Mood
import com.google.firebase.auth.FirebaseAuth

const val TAG = "FIRESTORE"
private lateinit var auth: FirebaseAuth
lateinit var viewModel: ViewModel

var globalNonTriggersList= mutableListOf<String>()
lateinit var globalUser: User
var globalMoodEntryDetails= Mood("", "",
"", mutableListOf(), mutableListOf(),
mutableListOf(), null)

class MainActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        //val inputStream: InputStream = assets.open("stopwords.txt")
        viewModel= ViewModel()


    }



}
