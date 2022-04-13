package com.example.elucidate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

//private lateinit var auth: FirebaseAuth
class ViewModel() {
    var firebaseUtils= FirebaseUtils()

    /*private var _globalMoodEntry=""

    var globalMoodEntry: String
    get()=_globalMoodEntry
    set(value) {}*/
    /*private val _moodEntry= MutableLiveData<String>()
    val moodEntry: LiveData<String> = _moodEntry*/
    private var _moodEntry= MutableLiveData<String>()
    val moodEntry: LiveData<String> get()=_moodEntry

    var _moodRating=MutableLiveData<String>()
    val moodRating: LiveData<String> get()=_moodRating

    var _keywordsList= MutableLiveData<MutableList<String>>(mutableListOf())
    val keywordsList: LiveData<MutableList<String>> get()=_keywordsList

    var _triggerWordsList= MutableLiveData<MutableList<String>>(mutableListOf())
    val triggerWordsList: LiveData<MutableList<String>> get()=_triggerWordsList

    var _nonTriggersList= MutableLiveData<MutableList<String>>(mutableListOf())
    val nonTriggersList: LiveData<MutableList<String>> get()=_nonTriggersList

    var _positiveWordsList= MutableLiveData<MutableList<String>>(mutableListOf())
    val positiveWordsList: LiveData<MutableList<String>> get() = _positiveWordsList

    fun enterMood(mood: String){
        _moodEntry.value=mood
    }
    fun enterMoodRating(moodRating: String){
        _moodRating.value=moodRating
    }
    fun logKeywords(keywords: MutableList<String>){
        _keywordsList.value=keywords
    }
    fun logTriggers(triggers: MutableList<String>){
        _triggerWordsList.value=triggers
    }
    fun logNonTriggers(nonTriggers: MutableList<String>){
       _nonTriggersList.value=nonTriggers
    }
    fun logPositiveWords(positiveWords: MutableList<String>){
        _positiveWordsList.value=positiveWords
    }



    /*var moodRating
    var keywordsList
    var triggerWordsList
    var nonTriggersList
    var positiveWordsList*/

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

    fun retrieveMoodEntryByDate(dateStart: Date, dateEnd: Date): String {
        return firebaseUtils.retrieveMoodEntryByDate(dateStart, dateEnd)

    }
    /*fun initializeAuth(auth: FirebaseAuth): FirebaseAuth{
        firebaseUtils.initializeAuth()
        return auth
    }*/
    fun getCurrentUserId(): String {
        val currentUser=firebaseUtils.getCurrentUserId()
        return currentUser

    }



}