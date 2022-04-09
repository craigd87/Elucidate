package com.example.elucidate

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

//lateinit var auth: FirebaseAuth
//Serves as a API to allow adding getting, deleting and updating (section.io link)
class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    var auth=Firebase.auth
    var userAuth = FirebaseAuth.getInstance().currentUser
    val uid= userAuth?.uid

    fun signup(email: String, password: String){
        auth.createUserWithEmailAndPassword ("$email", "$password")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")

                    //with help from TutorialsEU https://www.youtube.com/watch?v=8I5gCLaS25w

                    //val user: FirebaseUser = task.result!!.user!!



                    /*val action =
                        SignUpFragmentDirections.actionSignUpFragmentToUpdateProfileFragment(
                            //user.uid,
                            "$email",
                            "$password"
                        )*/
                }

            }

    }
    fun loginAfterSignup(email: String, password: String){
        FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser
            if (user!=null){
                Log.d("loginCheck", "user logged in")

            }else{
                auth.signInWithEmailAndPassword("$email", "$password")
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {

                            Log.d(ContentValues.TAG, "signInWithEmail:success")

                        } else {

                            Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)

                        }
                    }
            }
        }
    }

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword("$email", "$password")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "logInWithEmail:success")


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "logInWithEmail:failure", task.exception)
                    //Toast.makeText(,"Authentication failed.",
                      //  Toast.LENGTH_SHORT).show()

                }
            }
    }
    fun saveUserDetails(user: User): Task<Void> {
        //var
        var documentReference = fireStoreDatabase.collection("users").document("TESTING")

        return documentReference.set(user)
    }

    fun updateProfile(name: String){
        val profileUpdates =
            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()

        userAuth!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("windsor", "User profile updated.")

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "not updated", task.exception)

                }
            }
    }
    public override fun onStart() {

        auth.addAuthStateListener(FirebaseAuth.AuthStateListener {  })
    }

    public override fun onPause() {

        auth.removeAuthStateListener(FirebaseAuth.AuthStateListener {  })
    }
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