package com.example.elucidate

import java.io.Serializable

data class User (var id: String, var name: String): Serializable{
    constructor() : this("", ""
    )


    /*fun getUserName():String{
        var userName = "$name"
        return userName
    }*/
}