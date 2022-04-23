package com.example.elucidate

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.elucidate.databinding.FragmentRetreiveMoodEntriesBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
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

    fun signup(email: String, password: String, name: String){
        firebaseUtils.signup(email, password, name)
    }

    fun loginAfterSignup(email: String, password: String){
        firebaseUtils.loginAfterSignup(email, password)
    }

    fun login(email: String, password: String,): Task<AuthResult> {
       val task= firebaseUtils.login(email, password)
        //Log.d("Derry", id)
        return task

    }

    fun updateProfile(name: String){
        firebaseUtils.updateProfile(name)
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
    // get realtime updates from firebase regarding saved addresses
    fun retrieveMoodEntryByDate(dateStart: Date, dateEnd: Date) : LiveData<List<Mood>>{
        //firebaseUtils.retrieveMoodEntryByDate().whereGreaterThanOrEqualTo("time", dateStart)
        //.whereLessThan("time", dateEnd).addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
        firebaseUtils.retrieveMoodEntryByDate().whereGreaterThanOrEqualTo("time", dateStart)
            .whereLessThan("time", dateEnd).addSnapshotListener { snapshot, e ->

        if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                moodRetrieved.value = emptyList()
                return@addSnapshotListener
            }

            //var savedAddressList : MutableList<AddressItem> = mutableListOf()
            var moodItemList: MutableList<Mood> = mutableListOf()
            for (doc in snapshot!!) {
               var moodItem = doc.toObject(Mood::class.java)
                moodItemList.add(moodItem)
               // savedAddressList.add(addressItem)
            }
           moodRetrieved.value = moodItemList
        }

        return moodRetrieved
    }

    var allMoodsRetrieved : MutableLiveData<List<Mood>> = MutableLiveData()
    // get realtime updates from firebase regarding saved addresses
    fun retrieveAllMoodEntries(id: String) : LiveData<List<Mood>>{
        //firebaseUtils.retrieveMoodEntryByDate().whereGreaterThanOrEqualTo("time", dateStart)
        //.whereLessThan("time", dateEnd).addSnapshotListener(EventListener<QuerySnapshot> { value, e ->

        firebaseUtils.retrieveAllMoodEntries("$id").orderBy("time").addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    allMoodsRetrieved.value = emptyList()
                    return@addSnapshotListener
                }

                //var savedAddressList : MutableList<AddressItem> = mutableListOf()
                var moodItemList: MutableList<Mood> = mutableListOf()
                for (doc in snapshot!!) {
                    var moodItem = doc.toObject(Mood::class.java)
                    moodItemList.add(moodItem)
                    Log.d("list", moodItemList.toString())
                    // savedAddressList.add(addressItem)
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
        //var savedAddressList : MutableList<AddressItem> = mutableListOf()
        /*for (doc in documents!!) {
            var addressItem = doc.toObject(AddressItem::class.java)
            savedAddressList.add(addressItem)
        }
        savedAddresses.value = savedAddressList
    })*/



    /*fun initializeAuth(auth: FirebaseAuth): FirebaseAuth{
        firebaseUtils.initializeAuth()
        return auth
    }*/
    fun getCurrentUserId(): String {
        val currentUser=firebaseUtils.getCurrentUserId()
        return currentUser

    }



}