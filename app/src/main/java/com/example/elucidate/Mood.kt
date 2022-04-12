package com.example.elucidate

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import java.io.Serializable

data class Mood (var id: String, var moodEntry: String, var moodRating: String, var keywords: MutableList<String>,
                 var triggers: MutableList<String>, var positives: MutableList<String>, var time: Timestamp
): Serializable{


}