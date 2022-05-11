package com.example.elucidate.dto

import com.google.firebase.Timestamp
import java.io.Serializable

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */

/**
 *  A data class to create a [NonMoodEntry] object.
 */
class NonMoodEntry(var id: String,
                   var textEntry: String,
                   var keywords: MutableList<String>,
                   var time: Timestamp?
): Serializable {
    constructor() : this("", "",  mutableListOf(), null
    )
}