package com.example.elucidate.model

import android.content.ContentValues
import android.util.Log
import com.example.elucidate.dto.Mood
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

//private lateinit var auth: FirebaseAuth
//Serves as a API to allow adding getting, deleting and updating (section.io link)
class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    private val auth=Firebase.auth
    private val currentUser=auth.currentUser
    //var userAuth = FirebaseAuth.getInstance().currentUser
    //val uid= userAuth?.uid

    fun getCurrentUserId(): String{

        val currentUserId= currentUser?.uid.toString()
        return currentUserId

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

    /*fun loginAfterSignup(email: String, password: String){
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
    }*/

    fun login(email: String, password: String): Task<AuthResult>{
        //var userId=""
        //var idList= mutableListOf<String>()
        val task =auth.signInWithEmailAndPassword("$email", "$password")
            .addOnCompleteListener() { task ->
                /*if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    //view?.findNavController()?.navigate(R.id.action_loginFragment2_to_dashboardFragment)
                    val user : FirebaseUser = task.result!!.user!!
                    val userId=user.uid
                    idList.add(userId)
                    Log.d("wtf", userId)
                    Log.d("wtf2", idList.toString())*/

                    /*val userList=viewModel.retrieveUser(userId).observe(viewLifecycleOwner, Observer { it ->
                        Log.d("garfield", "$it")
                        retrievedMood = it as MutableList<Mood>
                        Log.d("retrieved mood", "$retrievedMood")
                        var mood: Mood
                        for (item in retrievedMood) {
                            mood = item
                    /*Log.d("ulist", "$userList")
                    for (item in userList){
                        Log.d("item", "Stark")
                        globalUser=User(item.id,item.name)
                    }*/
                    //return@addOnCompleteListener
                            */
               // } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.exception)
                    //Toast.makeText(context,"Authentication failed.", Toast.LENGTH_SHORT).show()

                //}

                //return user
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
        //Log.d("wtf3", idList.toString())
        //return idList.toString()
        return task
    }

    fun saveUserDetails(user: User): Task<Void> {

        var documentReference = fireStoreDatabase.collection("users").document()

        return documentReference.set(user)
    }
    fun saveMoodDetails(mood: Mood): Task<Void> {

        var documentReference = fireStoreDatabase.collection("userMoods").document()

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
    fun retrieveMoodEntry(): CollectionReference {
        var collectionReference = FirebaseUtils().fireStoreDatabase.collection("userMoods")
        return collectionReference
    }

    fun retrieveAllMoodEntries(id: String): Query {
        var queryRef = FirebaseUtils().fireStoreDatabase.collection("userMoods").whereEqualTo("id", "$id")
        return queryRef
    }


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