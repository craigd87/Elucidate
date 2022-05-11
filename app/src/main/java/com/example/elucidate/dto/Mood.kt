package com.example.elucidate.dto

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import java.io.Serializable
import java.util.*

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */

/**
 * Concepts and aspects of code relating to DTOs, FirebaseUtils and ViewModel courtesy of:
 * https://medium.com/@deepak140596/firebase-firestore-using-view-models-and-livedata-f9a012233917
 * Deepak Prasad
 * Feb 24, 2019
 */

/**
 * A data class to create a [Mood] object.
 */
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