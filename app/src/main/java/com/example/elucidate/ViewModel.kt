package com.example.elucidate

import android.app.backup.BackupAgent
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth


class ViewModel() {
    var firebaseUtils= FirebaseUtils()


    fun saveUserDetailsToFirestore(user: User){
        firebaseUtils.saveUserDetails(user).addOnFailureListener {
            Log.e("vmtest","Failed to save Address!")
        }.addOnSuccessListener {
            Log.d("slainte!", "working!")
        }
    }

    fun signup(email: String, password: String, name: String, age: String){
        firebaseUtils.signup(email, password, name, age)
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