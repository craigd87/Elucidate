package com.example.elucidate

import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth


class ViewModel() {
    var firebaseUtils= FirebaseUtils()


    fun saveUserDetailsToFirestore(user: User){
        firebaseUtils.saveUserDetails(user).addOnFailureListener {
            Log.e("vmtest","Failed to save Address!")
        }
    }

    fun signup(email: String, password: String){
        firebaseUtils.signup(email, password)
    }

    fun loginAfterSignup(email: String, password: String){
        firebaseUtils.loginAfterSignup(email, password)
    }

    fun login(email: String, password: String,){
        firebaseUtils.login(email, password)
    }

    fun updateProfile(name: String){
        firebaseUtils.updateProfile(name)
    }



}