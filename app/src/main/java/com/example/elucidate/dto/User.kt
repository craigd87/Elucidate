package com.example.elucidate.dto

import java.io.Serializable

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */

/**
 * A data class to create a [User] object.
 */
data class User (var id: String, var name: String): Serializable{
    constructor() : this("", ""
    )

}