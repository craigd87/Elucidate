package com.example.elucidate

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

//lateinit var auth: FirebaseAuth
//Serves as a API to allow adding getting, deleting and updating (section.io link)
class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    var userAuth = FirebaseAuth.getInstance().currentUser
    val uid= userAuth?.uid

    fun saveUserDetails(user: User): Task<Void> {
        //var
        var documentReference = fireStoreDatabase.collection("users").document("TESTING")

        return documentReference.set(user)
    }
    //auth= Firebase.auth


    /*fun printName(id :String){
        fireStoreDatabase.collection("users").whereEqualTo("id", "$id")
            .get()
            /*.addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("exist", "DocumentSnapshot data: ${document.data}")
                    val name = document.getString("name").toString()
                    binding.tvDashWelcome.text = "Hi " + name+"!"

                }

            }*/

    }*/
}