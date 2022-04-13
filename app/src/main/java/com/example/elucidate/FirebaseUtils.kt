package com.example.elucidate

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import java.util.*

//private lateinit var auth: FirebaseAuth
//Serves as a API to allow adding getting, deleting and updating (section.io link)
class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    val auth=Firebase.auth
    var userAuth = FirebaseAuth.getInstance().currentUser
    val uid= userAuth?.uid

    /*fun initializeAuth(): FirebaseAuth{
        auth=Firebase.auth
        return auth
    }*/
    fun getCurrentUserId(): String{
        val currentUser=auth.currentUser
        val currentUserId= currentUser?.uid.toString()
        return currentUserId

    }
    fun signup(email: String, password: String, name: String, age: String) {

        auth.createUserWithEmailAndPassword ("$email", "$password")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")

                    //with help from TutorialsEU https://www.youtube.com/watch?v=8I5gCLaS25w

                    val user : FirebaseUser = task.result!!.user!!
                    val userId=user.uid

                    val userDetails=User("$userId", "$name", "$age")
                    viewModel.saveUserDetailsToFirestore(userDetails)

                    /*val action = SignUpFragmentDirections.actionSignUpFragmentToUpdateProfileFragment( "$email","$password", "$userId")
                    //val action2= SignUpFragmentDirections.actionSignUpFragmentToUpdateProfileFragment()
                    view?.findNavController()?.navigate(action)*/


                }

            }
        /*auth.createUserWithEmailAndPassword ("$email", "$password")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")

                    //with help from TutorialsEU https://www.youtube.com/watch?v=8I5gCLaS25w

                    val currentUser: FirebaseUser = task.result!!.user!!
                    val id = currentUser.uid
                    val user = User("$id", "$name", "$age")
                    viewModel.saveUserDetailsToFirestore(user)

                    /*val action =
                        SignUpFragmentDirections.actionSignUpFragmentToUpdateProfileFragment(
                            //user.uid,
                            "$email",
                            "$password"
                        )*/
                }

            }*/
    //return user
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

                            val currentUser: FirebaseUser = task.result!!.user!!
                            if(currentUser!=null){
                                Log.d("queen2", "hearts")

                            }else{
                                Log.d("king2", "user is still null")
                            }
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
                    Log.d(TAG, "signInWithEmail:success")
                    //view?.findNavController()?.navigate(R.id.action_loginFragment2_to_dashboardFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    //Toast.makeText(context,"Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }
        /*auth.signInWithEmailAndPassword("$email", "$password")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "logInWithEmail:success")
                    val currentUser: FirebaseUser = task.result!!.user!!
                    if(currentUser!=null){
                        Log.d("queen", "hearts")

                    }else{
                        Log.d("king", "user is still null")
                    }


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "logInWithEmail:failure", task.exception)
                    //Toast.makeText(,"Authentication failed.",
                      //  Toast.LENGTH_SHORT).show()

                }
            }*/
    }
    fun saveUserDetails(user: User): Task<Void> {
        //var
        var documentReference = fireStoreDatabase.collection("users").document("TESTING")

        return documentReference.set(user)
    }
    fun saveMoodDetails(mood: Mood): Task<Void> {
        //var
        var documentReference = fireStoreDatabase.collection("userMoods").document("TESTINGMOOD2")

        return documentReference.set(mood)
    }

    /*fun retrieveMoodEntryByDate(dateStart: Date, dateEnd: Date): MutableLiveData<String> {

        var mutableLiveData= MutableLiveData<String>()

        val queryRef = FirebaseUtils().fireStoreDatabase.collection("userMoods")
        val moodEntry= queryRef.whereGreaterThanOrEqualTo("time", dateStart).whereLessThan("time", dateEnd).get().addOnSuccessListener { documents ->
            for (document in documents) {
                if (document != null) {
                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    //moodEntry = document.getString("moodRating").toString()
                    val docString =document.getString("moodEntry").toString()
                    mutableLiveData.value=docString

                }
            }
        }

        return mutableLiveData
    }*/
    fun retrieveMoodEntryByDate(): CollectionReference {
        var collectionReference = FirebaseUtils().fireStoreDatabase.collection("userMoods/MoodEntry")
        return collectionReference
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