package com.example.elucidate

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import java.io.Serializable

data class Mood (var id: String?=null,
                 var moodEntry: String?=null,
                 var moodRating: String?=null,
                 var keywords: MutableList<String>,
                 var triggers: MutableList<String>,
                 var positives: MutableList<String>,
                 var time: Timestamp
): Serializable{


}