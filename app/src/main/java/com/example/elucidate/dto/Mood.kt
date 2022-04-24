package com.example.elucidate.dto

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import java.io.Serializable
import java.util.*

//https://medium.com/@deepak140596/firebase-firestore-using-view-models-and-livedata-f9a012233917
//also find SO links for no-argument constructor error
data class Mood (var id: String,
                 var moodEntry: String,
                 var moodRating: String,
                 var keywords: MutableList<String>,
                 var triggers: MutableList<String>,
                 var positives: MutableList<String>,
                 var time: Timestamp?
): Serializable{
   constructor() : this("", "",
        "", mutableListOf(), mutableListOf(),
        mutableListOf(), null
    )

}