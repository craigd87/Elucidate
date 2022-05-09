package com.example.elucidate.dto

import java.io.Serializable

/**
 * A data class to create a [User] object.
 */
data class User (var id: String, var name: String): Serializable{
    constructor() : this("", ""
    )

}