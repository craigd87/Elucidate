package com.example.elucidate.dto

import java.io.Serializable

data class User (var id: String, var name: String): Serializable{
    constructor() : this("", ""
    )

}