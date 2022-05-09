package com.example.elucidate.dto

import com.google.firebase.Timestamp
import java.io.Serializable

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