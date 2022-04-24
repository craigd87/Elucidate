package com.example.elucidate.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.elucidate.dto.Mood
import com.example.elucidate.dto.User
import com.example.elucidate.model.FirebaseUtils
import com.example.elucidate.TAG
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

//private lateinit var auth: FirebaseAuth
class ViewModel() {
    var firebaseUtils= FirebaseUtils()


    fun saveUserDetailsToFirestore(user: User){
        firebaseUtils.saveUserDetails(user).addOnFailureListener {
            Log.e("vmtest","Failed to save Address!")
        }.addOnSuccessListener {
            Log.d("slainte!", "working!")
        }
    }

    fun saveMoodDetailsToFirestore(mood: Mood){
        firebaseUtils.saveMoodDetails(mood).addOnFailureListener {
            Log.e("vmtest","Failed to save Address!")
        }.addOnSuccessListener {
            Log.d("slainte!", "working!")
        }
    }

    fun signup(email: String, password: String, name: String){
        firebaseUtils.signup(email, password, name)
    }

    /*fun loginAfterSignup(email: String, password: String){
        firebaseUtils.loginAfterSignup(email, password)
    }*/

    fun login(email: String, password: String,): Task<AuthResult> {
       val task= firebaseUtils.login(email, password)
        //Log.d("Derry", id)
        return task

    }



    /*fun retrieveMoodEntryByDate(dateStart: Date, dateEnd: Date): String {
        firebaseUtils.retrieveMoodEntryByDate(dateStart, dateEnd).observe(this, observer:Observer)

    }*/
    /*var moodRetrieved : MutableLiveData<String> = MutableLiveData()
    fun retrieveMoodEntryByDate(dateStart: Date, dateEnd:Date){
        firebaseUtils.retrieveMoodEntryByDate().whereGreaterThanOrEqualTo("time", dateStart)
            .whereLessThan("time", dateEnd).get().addOnSuccessListener { documents ->
            for (document in documents) {
                if (document != null) {
                    Log.d("exist", "DocumentSnapshot data: ${document.data}")

                    //moodEntry = document.getString("moodRating").toString()
                    val docString =document.getString("moodEntry").toString()
                    moodRetrieved.value=docString

                }
            }
        }*/
    var moodRetrieved : MutableLiveData<List<Mood>> = MutableLiveData()
    // get realtime updates from firebase regarding saved moods

    fun retrieveMoodEntryByDate(dateStart: Date, dateEnd: Date) : LiveData<List<Mood>>{

        firebaseUtils.retrieveMoodEntryByDate().whereGreaterThanOrEqualTo("time", dateStart)
            .whereLessThan("time", dateEnd).addSnapshotListener { snapshot, e ->

        if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                moodRetrieved.value = emptyList()
                return@addSnapshotListener
            }

            var moodItemList: MutableList<Mood> = mutableListOf()
            for (doc in snapshot!!) {
               var moodItem = doc.toObject(Mood::class.java)
                moodItemList.add(moodItem)

            }
           moodRetrieved.value = moodItemList
        }

        return moodRetrieved
    }

    var allMoodsRetrieved : MutableLiveData<List<Mood>> = MutableLiveData()
    // get realtime updates from firebase regarding saved moods
    fun retrieveAllMoodEntries(id: String) : LiveData<List<Mood>>{

        firebaseUtils.retrieveAllMoodEntries("$id").orderBy("time").addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    allMoodsRetrieved.value = emptyList()
                    return@addSnapshotListener
                }

                var moodItemList: MutableList<Mood> = mutableListOf()
                for (doc in snapshot!!) {
                    var moodItem = doc.toObject(Mood::class.java)
                    moodItemList.add(moodItem)
                    Log.d("list", moodItemList.toString())

                }
                allMoodsRetrieved.value = moodItemList
            }
        Log.d("sancho", "moo")

        return allMoodsRetrieved
    }
    var retrievedUsers= mutableListOf<User>()
    //var retrievedUsers : MutableLiveData<List<User>> = MutableLiveData()
    fun retrieveUser(id: String): Task<QuerySnapshot> {
        Log.d("oak", id)
        /*firebaseUtils.retrieveUser().whereEqualTo("id", id).addSnapshotListener { snapshot, e ->
            Log.d("Idris", "got to here")
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                retrievedUsers.value = emptyList()
                return@addSnapshotListener
            }
            Log.d("snip","$snapshot")

            //var savedAddressList : MutableList<AddressItem> = mutableListOf()
            var userList: MutableList<User> = mutableListOf()
            for (doc in snapshot!!) {
                Log.d("dox", "$doc")
                var retrievedUser = doc.toObject(User::class.java)
                Log.d("retuse", "$retrievedUser")
                userList.add(retrievedUser)
                Log.d("userlist", userList.toString())
                // savedAddressList.add(addressItem)
            }
            retrievedUsers.value = userList
        }
        Log.d("sancho", retrievedUsers.value.toString())

        return retrievedUsers*/
            val query=firebaseUtils.retrieveUser().whereEqualTo("id", "$id").get()
            /*.addOnSuccessListener { documents ->
                for (document in documents) {
                    if (document != null) {
                        Log.d("exist", "DocumentSnapshot data: ${document.data}")

                        //moodEntry = document.getString("moodRating").toString()
                        //val retrievedUser =
                    //document.toObject(User::class.java)
                        //retrievedUsers.add(retrievedUser)


                    }else{
                        Log.d("pain", "null")
                    }
                }

            }*/
        //Log.d("Elba", "$retrievedUsers")
        return query
    }

    fun getCurrentUserId(): String {
        val currentUser=firebaseUtils.getCurrentUserId()
        return currentUser

    }

}