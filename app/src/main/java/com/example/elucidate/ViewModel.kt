package com.example.elucidate

import android.util.Log
import com.google.firebase.auth.FirebaseAuth


class ViewModel() {
    var firebaseUtils= FirebaseUtils()


    fun saveUserDetailsToFirestore(user: User){
        firebaseUtils.saveUserDetails(user).addOnFailureListener {
            Log.e("vmtest","Failed to save Address!")
        }
    }
}