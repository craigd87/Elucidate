package com.example.elucidate.model

import android.content.ContentValues
import android.util.Log
import com.example.elucidate.dto.Mood
import com.example.elucidate.dto.NonMoodEntry
import com.example.elucidate.dto.User
import com.example.elucidate.view.viewmodel.ViewModel
import com.example.elucidate.viewModel

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import java.util.*


//Serves as a API to allow adding getting, deleting and updating (section.io link)
class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    private val auth=Firebase.auth
    private val currentUser=auth.currentUser
    //var userAuth = FirebaseAuth.getInstance().currentUser
    //val uid= userAuth?.uid

    fun getCurrentUser(): FirebaseUser?{

        return currentUser
    }

    fun getCurrentUserId(): String{

        val currentUserId= currentUser?.uid.toString()
        return currentUserId

    }

    fun getCurrentUserName(id: String): Query{
        var queryRef = FirebaseUtils().fireStoreDatabase.collection("users").whereEqualTo("id", "$id")
        return queryRef
    }

    fun signup(email: String, password: String, name: String) {

        auth.createUserWithEmailAndPassword ("$email", "$password")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")

                    //with help from TutorialsEU https://www.youtube.com/watch?v=8I5gCLaS25w

                    val user : FirebaseUser = task.result!!.user!!
                    val userId=user.uid

                    val userDetails= User("$userId", "$name")
                    viewModel.saveUserDetailsToFirestore(userDetails)

                }
            }
    }

    fun saveUserDetails(user: User): Task<Void> {

        var documentReference = fireStoreDatabase.collection("users").document()

        return documentReference.set(user)
    }

    fun saveMoodDetails(mood: Mood): Task<Void> {

        var documentReference = fireStoreDatabase.collection("userMoods").document()

        return documentReference.set(mood)
    }

    fun saveGeneralEntryDetails(nonMoodEntry: NonMoodEntry): Task<Void> {

        var documentReference = fireStoreDatabase.collection("userNonMoods").document()
        return documentReference.set(nonMoodEntry)
    }

    fun retrieveMoodEntry(): CollectionReference {
        var collectionReference = FirebaseUtils().fireStoreDatabase.collection("userMoods")
        return collectionReference
    }

    fun retrieveGeneralEntry(): CollectionReference {
        var collectionReference = FirebaseUtils().fireStoreDatabase.collection("userNonMoods")
        return collectionReference
    }

    fun retrieveAllMoodEntries(id: String): Query {
        var queryRef = FirebaseUtils().fireStoreDatabase.collection("userMoods").whereEqualTo("id", "$id")
        return queryRef
    }

    fun retrieveAllGeneralEntries(id: String): Query {
        var queryRef = FirebaseUtils().fireStoreDatabase.collection("userNonMoods").whereEqualTo("id", "$id")
        return queryRef
    }

    /*fun retrieveMoodByKeyword(id:String, keyword: String): Query {
        var queryRef=FirebaseUtils().fireStoreDatabase.collection("userMoods").whereEqualTo("id", "$id")
    }*/


    /*fun retrieveMoodEntriesForTimePeriod(id: String, date: Date): Query {
        var queryRef = FirebaseUtils().fireStoreDatabase.collection("userMoods").whereEqualTo("id", "$id")
        return queryRef
    }*/

    /*fun updateProfile(name: String){
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
    }*/

    fun retrieveUser(): CollectionReference{
        var collectionReference = FirebaseUtils().fireStoreDatabase.collection("users")
        return collectionReference
    }

}