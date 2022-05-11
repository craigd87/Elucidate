package com.example.elucidate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elucidate.view.viewmodel.ViewModel
import com.example.elucidate.dto.User
import com.example.elucidate.databinding.ActivityMainBinding
import com.example.elucidate.dto.Mood
import com.example.elucidate.dto.NonMoodEntry
import com.google.firebase.auth.FirebaseAuth

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */
const val TAG = "FIRESTORE"

lateinit var viewModel: ViewModel

var globalNonTriggersList= mutableListOf<String>()
lateinit var globalUser: User
var globalMoodEntryDetails= Mood("", "",
"", mutableListOf(), mutableListOf(),
mutableListOf(), null)
var globalGeneralEntryDetails= NonMoodEntry("","", mutableListOf(),null)

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        viewModel= ViewModel()

    }

}
