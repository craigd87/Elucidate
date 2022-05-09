package com.example.elucidate.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.elucidate.DateMillisCreator
import com.example.elucidate.model.FirebaseUtils
import com.example.elucidate.TAG
import com.example.elucidate.dto.*
import com.example.elucidate.globalUser
import com.example.elucidate.viewModel
import com.github.mikephil.charting.data.Entry
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.text.SimpleDateFormat
import java.util.*

//private lateinit var auth: FirebaseAuth
class ViewModel() {

    //Instance of FirebaseUtils
    private var firebaseUtils= FirebaseUtils()

    //Vals for working out date ranges
    private val calendar= Calendar.getInstance()
    private val currentDate= calendar.time
    private val dateCreator=DateMillisCreator()
    private val dateEndTimeMillis=calendar.timeInMillis
    private val sdf=SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    private val millisFor7Days: Long= 604800000
    private val millisFor30Days: Long= 2592000000
    private val formatedDate = SimpleDateFormat("yyyy/MM/dd").format(currentDate)
    private val simpleDateStart="$formatedDate 00:00:00"
    private val simpleDateEnd="$formatedDate 23:59:59"
    private val dateStartTime=dateCreator.getMilliseconds(simpleDateStart)
    private val dateEndTime=dateCreator.getMilliseconds(simpleDateEnd)

    //MutableLiveData lists
    private var keywordMoodsRetrieved : MutableLiveData<List<Mood>> = MutableLiveData()
    private var keywordGeneralRetrieved : MutableLiveData<List<NonMoodEntry>> = MutableLiveData()
    private var moodRetrieved : MutableLiveData<List<Mood>> = MutableLiveData()
    private var entryRetrieved : MutableLiveData<List<NonMoodEntry>> = MutableLiveData()
    private var allMoodsRetrieved : MutableLiveData<List<Mood>> = MutableLiveData()
    private var allEntriesRetrieved : MutableLiveData<List<NonMoodEntry>> = MutableLiveData()

    /*fun getCurrentUser():FirebaseUser?{
        val currentUser=firebaseUtils.getCurrentUser()
        return currentUser
    }*/

    /**
     * Saves a [user] to the database.
     */
    fun saveUserDetailsToFirestore(user: User){
        firebaseUtils.saveUserDetails(user).addOnFailureListener {
            Log.e("SavedUserFail","Failed to save user")
        }.addOnSuccessListener {
            Log.d("SavedUserSuccess", "User succesfully saved")
        }
    }

    /**
     * Saves a [mood] to the database.
     */
    fun saveMoodDetailsToFirestore(mood: Mood){
        firebaseUtils.saveMoodDetails(mood).addOnFailureListener {
            Log.e("SavedMoodFail","Failed to save mood")
        }.addOnSuccessListener {
            Log.d("SavedMoodSuccess", "Mood successfully saved")
        }
    }

    /**
     * Saves a [nonMoodEntry] to the database.
     */
    fun saveGeneralEntryDetailsToFirestore(nonMoodEntry: NonMoodEntry){
        firebaseUtils.saveGeneralEntryDetails(nonMoodEntry).addOnFailureListener {
            Log.e("SavedEntryFail","Failed to save entry")
        }.addOnSuccessListener {
            Log.d("SavedEntrySuccess", "Entry successfully saved")
        }
    }

    /**
     * Creates a user account on the database with the given [email] and [password].
     */
    fun signup(email: String, password: String, name: String){
        firebaseUtils.signup(email, password, name)
    }

    /**
     * Retrieves a user from the database using the given associated [id].
     */
    fun retrieveUser(id: String): Task<QuerySnapshot> {
        Log.d("oak", id)

        val query=firebaseUtils.retrieveUser(id)

        return query
    }

    fun retrieveMoodEntryByDateDesc(id: String, dateStart: Date, dateEnd: Date) : LiveData<List<Mood>>{

        firebaseUtils.retrieveMoodEntriesByDateDesc(id,dateStart,dateEnd).addSnapshotListener { snapshot, e ->

        if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                moodRetrieved.value = emptyList()
                return@addSnapshotListener
            }

            val moodItemList: MutableList<Mood> = mutableListOf()
            for (doc in snapshot!!) {
               val moodItem = doc.toObject(Mood::class.java)
                moodItemList.add(moodItem)

            }
           moodRetrieved.value = moodItemList
        }

        return moodRetrieved
    }

    fun retrieveGeneralEntryByDateDesc(id: String, dateStart: Date, dateEnd: Date) : LiveData<List<NonMoodEntry>>{

        firebaseUtils.retrieveGeneralEntriesByDateDesc(id,dateStart,dateEnd).addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    entryRetrieved.value = emptyList()
                    return@addSnapshotListener
                }

                val entryItemList: MutableList<NonMoodEntry> = mutableListOf()
                for (doc in snapshot!!) {
                    val entryItem = doc.toObject(NonMoodEntry::class.java)
                    entryItemList.add(entryItem)

                }
                entryRetrieved.value = entryItemList
            }

        return entryRetrieved
    }

    fun retrieveMoodEntryByDateAsc(id: String, dateStart: Date, dateEnd: Date) : LiveData<List<Mood>>{

        firebaseUtils.retrieveMoodEntriesByDateAsc(id,dateStart,dateEnd).addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    moodRetrieved.value = emptyList()
                    return@addSnapshotListener
                }

                val moodItemList: MutableList<Mood> = mutableListOf()
                for (doc in snapshot!!) {
                    val moodItem = doc.toObject(Mood::class.java)
                    moodItemList.add(moodItem)

                }
                moodRetrieved.value = moodItemList
            }

        return moodRetrieved
    }

    fun retrieveGeneralEntryByDateAsc(id: String, dateStart: Date, dateEnd: Date) : LiveData<List<NonMoodEntry>>{

        firebaseUtils.retrieveGeneralEntriesByDateAsc(id,dateStart,dateEnd).addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    entryRetrieved.value = emptyList()
                    return@addSnapshotListener
                }

                val entryItemList: MutableList<NonMoodEntry> = mutableListOf()
                for (doc in snapshot!!) {
                    val entryItem = doc.toObject(NonMoodEntry::class.java)
                    entryItemList.add(entryItem)

                }
                entryRetrieved.value = entryItemList
            }

        return entryRetrieved
    }


    fun retrieveCurrentDayMood(id: String): LiveData<List<Mood>>{

        val retrieved=viewModel.retrieveMoodEntryByDateDesc(id, dateStartTime,dateEndTime)

        return retrieved
    }

    fun retrieveCurrentDayGeneral(id: String): LiveData<List<NonMoodEntry>>{

        val retrieved=viewModel.retrieveGeneralEntryByDateDesc(id, dateStartTime,dateEndTime)

        return retrieved
    }

    fun retrieveDayRangeMoodsAsc(id: String, numberOfDays: Int): LiveData<List<Mood>>{
        var millisRange: Long=0
        when(numberOfDays){

            7 -> millisRange=millisFor7Days
            30 -> millisRange=millisFor30Days
        }
        val dateStartTimeMillis= dateEndTimeMillis-millisRange
        val dateStartTime=Date(dateStartTimeMillis)
        val retrieved=viewModel.retrieveMoodEntryByDateAsc(id, dateStartTime,dateEndTime)

        return retrieved
    }

    fun retrieveDayRangeGeneralAsc(id: String, numberOfDays: Int): LiveData<List<NonMoodEntry>>{
        var millisRange: Long=0
        when(numberOfDays){

            7 -> millisRange=millisFor7Days
            30 -> millisRange=millisFor30Days
        }
        val dateStartTimeMillis= dateEndTimeMillis-millisRange
        val dateStartTime=Date(dateStartTimeMillis)
        val retrieved=viewModel.retrieveGeneralEntryByDateAsc(id, dateStartTime,dateEndTime)

        return retrieved
    }

    fun retrieveDayRangeMoodsDesc(id: String, numberOfDays: Int): LiveData<List<Mood>>{
        var millisRange: Long=0
        when(numberOfDays){

            7 -> millisRange=millisFor7Days
            30 -> millisRange=millisFor30Days
        }
        val dateStartTimeMillis= dateEndTimeMillis-millisRange
        val dateStartTime=Date(dateStartTimeMillis)
        val retrieved=viewModel.retrieveMoodEntryByDateDesc(id, dateStartTime,dateEndTime)

        return retrieved
    }

    fun retrieveDayRangeGeneralDesc(id: String, numberOfDays: Int): LiveData<List<NonMoodEntry>>{
        var millisRange: Long=0
        when(numberOfDays){

            7 -> millisRange=millisFor7Days
            30 -> millisRange=millisFor30Days
        }
        val dateStartTimeMillis= dateEndTimeMillis-millisRange
        val dateStartTime=Date(dateStartTimeMillis)
        val retrieved=viewModel.retrieveGeneralEntryByDateDesc(id, dateStartTime,dateEndTime)

        return retrieved
    }

    fun retrieveAllMoodEntries(id: String) : LiveData<List<Mood>>{

        firebaseUtils.retrieveAllMoodEntriesByTime(id).addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    allMoodsRetrieved.value = emptyList()
                    return@addSnapshotListener
                }

                val moodItemList: MutableList<Mood> = mutableListOf()
                for (doc in snapshot!!) {
                    val moodItem = doc.toObject(Mood::class.java)
                    moodItemList.add(moodItem)
                    Log.d("list", moodItemList.toString())

                }
                allMoodsRetrieved.value = moodItemList
            }
        Log.d("sancho", "moo")

        return allMoodsRetrieved
    }

    fun retrieveAllGeneralEntries(id: String) : LiveData<List<NonMoodEntry>>{

        firebaseUtils.retrieveAllGeneralEntriesByTime(id).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                allEntriesRetrieved.value = emptyList()
                return@addSnapshotListener
            }

            val entryItemList: MutableList<NonMoodEntry> = mutableListOf()
            for (doc in snapshot!!) {
                val entryItem = doc.toObject(NonMoodEntry::class.java)
                entryItemList.add(entryItem)
                Log.d("list", entryItemList.toString())

            }
            allEntriesRetrieved.value = entryItemList
        }
        Log.d("sancho", "moo")

        return allEntriesRetrieved
    }

    fun accessRetrievedMoodListData(list: List<Mood>): MutableList<MoodView>{

        val moodEntries= mutableListOf<MoodView>()
        var mood: Mood
        val retrievedMood = list as MutableList<Mood>
        moodEntries.clear()

        for (item in retrievedMood) {

            mood = item
            val entry = mood.moodEntry
            val time = mood.time?.toDate()
            val formatedTime = SimpleDateFormat("HH:mm -dd/MM/yyyy").format(time)
            val moodView = MoodView(entry, formatedTime)
            moodEntries.add(moodView)
        }

        return moodEntries
    }

    fun accessRetrievedGeneralListData(list: List<NonMoodEntry>): MutableList<NonMoodView>{

        val generalEntries= mutableListOf<NonMoodView>()
        var general: NonMoodEntry
        val retrievedEntry = list as MutableList<NonMoodEntry>
        generalEntries.clear()

        for (item in retrievedEntry) {

            general = item
            val entry = general.textEntry
            val time = general.time?.toDate()
            val formatedTime = SimpleDateFormat("HH:mm -dd/MM/yyyy").format(time)
            val nonMoodView = NonMoodView(entry, formatedTime)
            generalEntries.add(nonMoodView)
        }

        return generalEntries
    }

    fun accessRetrievedMoodRatingData(list: List<Mood>): MutableList<Float>{

        val moodEntries= mutableListOf<Float>()
        var mood: Mood
        val retrievedMood = list as MutableList<Mood>
        moodEntries.clear()

        for (item in retrievedMood) {

            mood = item
            val moodRating = mood.moodRating.toFloat()
            moodEntries.add(moodRating)
        }

        return moodEntries
    }

    fun accessRetrievedWordsData(list: List<Mood>, type: String): MutableList<String>{

        val moodKeywords= mutableListOf<String>()
        var mood: Mood
        val retrievedMood = list as MutableList<Mood>
        moodKeywords.clear()

        for (item in retrievedMood) {

            mood = item
            var keywordList= mutableListOf<String>()
            when(type) {
                "keywords" -> keywordList=mood.keywords
                "triggers" -> keywordList=mood.triggers
                "positives" -> keywordList=mood.positives
            }

            for (word in keywordList){
                moodKeywords.add(word)

            }
        }

        return moodKeywords
    }

    fun accessRetrievedGeneralWordsData(list: List<NonMoodEntry>, type: String): MutableList<String>{

        val keywords= mutableListOf<String>()
        var entry: NonMoodEntry
        val retrievedEntry = list as MutableList<NonMoodEntry>
        keywords.clear()

        for (item in retrievedEntry) {

            entry = item
            var keywordList= mutableListOf<String>()
            keywordList=entry.keywords

            for (word in keywordList){
                keywords.add(word)

            }
        }

        return keywords
    }

    fun getCurrentUserName(id: String): Task<QuerySnapshot>{

        val query= firebaseUtils.getCurrentUserName(id).get()

        return query
    }


    fun retrieveMoodByKeyword(id: String, keyword: String):  LiveData<List<Mood>>{

        firebaseUtils.retrieveMoodByKeyword(id, keyword).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                keywordMoodsRetrieved.value = emptyList()
                return@addSnapshotListener
            }

            val moodItemList: MutableList<Mood> = mutableListOf()
            for (doc in snapshot!!) {
                val moodItem = doc.toObject(Mood::class.java)
                moodItemList.add(moodItem)
                Log.d("list", moodItemList.toString())

            }
            keywordMoodsRetrieved.value = moodItemList
        }

        return keywordMoodsRetrieved
    }

    fun retrieveGeneralByKeyword(id: String, keyword: String):  LiveData<List<NonMoodEntry>>{

        firebaseUtils.retrieveGeneralByKeyword(id, keyword).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                keywordGeneralRetrieved.value = emptyList()
                return@addSnapshotListener
            }

            val generalItemList: MutableList<NonMoodEntry> = mutableListOf()
            for (doc in snapshot!!) {
                val entryItem = doc.toObject(NonMoodEntry::class.java)
                generalItemList.add(entryItem)
                Log.d("list", generalItemList.toString())

            }
            keywordGeneralRetrieved.value = generalItemList
        }

        return keywordGeneralRetrieved
    }

}