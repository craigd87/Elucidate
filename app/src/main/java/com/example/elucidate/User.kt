package com.example.elucidate

import java.io.Serializable

data class User (var id: String, var name: String, var age: String): Serializable{


    /*fun getUserName():String{
        var userName = "$name"
        return userName
    }*/
}