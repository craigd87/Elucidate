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
    private var firebaseUtils= FirebaseUtils()

    //vals for working out date ranges
    private val calendar= Calendar.getInstance()
    private val currentDate= calendar.time
    private val dateCreator=DateMillisCreator()
    private val dateEndTimeMillis=calendar.timeInMillis
    private val sdf=SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    private val millisFor7Days: Long= 604800000
    private val millisFor30Days: Long= 2592000000
    val formatedDate = SimpleDateFormat("yyyy/MM/dd").format(currentDate)
    val simpleDateStart="$formatedDate 00:00:00"
    val simpleDateEnd="$formatedDate 23:59:59"
    val dateStartTime=dateCreator.getMilliseconds(simpleDateStart)
    val dateEndTime=dateCreator.getMilliseconds(simpleDateEnd)


    private var keywordMoodsRetrieved : MutableLiveData<List<Mood>> = MutableLiveData()
    private var keywordGeneralRetrieved : MutableLiveData<List<NonMoodEntry>> = MutableLiveData()


    var moodRetrieved : MutableLiveData<List<Mood>> = MutableLiveData()
    var entryRetrieved : MutableLiveData<List<NonMoodEntry>> = MutableLiveData()

    fun getCurrentUser():FirebaseUser?{
        val currentUser=firebaseUtils.getCurrentUser()
        return currentUser
    }
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

    fun saveGeneralEntryDetailsToFirestore(nonMoodEntry: NonMoodEntry){
        firebaseUtils.saveGeneralEntryDetails(nonMoodEntry).addOnFailureListener {
            Log.e("vmtest","Failed to save Address!")
        }.addOnSuccessListener {
            Log.d("slainte!", "working!")
        }
    }

    fun signup(email: String, password: String, name: String){
        firebaseUtils.signup(email, password, name)
    }


    fun retrieveUser(id: String): Task<QuerySnapshot> {
        Log.d("oak", id)

        val query=firebaseUtils.retrieveUser().whereEqualTo("id", "$id").get()

        return query
    }

    fun getCurrentUserId(): String {
        val currentUser=firebaseUtils.getCurrentUserId()
        return currentUser

    }

    // get realtime updates from firebase regarding saved moods
    fun retrieveMoodEntryByDateDesc(id: String, dateStart: Date, dateEnd: Date) : LiveData<List<Mood>>{

        firebaseUtils.retrieveMoodEntry().whereEqualTo("id", "$id").whereGreaterThanOrEqualTo("time", dateStart)
            .whereLessThanOrEqualTo("time", dateEnd).orderBy("time",
                Query.Direction.DESCENDING).addSnapshotListener { snapshot, e ->

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

        firebaseUtils.retrieveGeneralEntry().whereEqualTo("id", "$id").whereGreaterThanOrEqualTo("time", dateStart)
            .whereLessThanOrEqualTo("time", dateEnd).orderBy("time",
                Query.Direction.DESCENDING).addSnapshotListener { snapshot, e ->

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

        firebaseUtils.retrieveMoodEntry().whereEqualTo("id", "$id").whereGreaterThanOrEqualTo("time", dateStart)
            .whereLessThanOrEqualTo("time", dateEnd).orderBy("time",
                Query.Direction.ASCENDING).addSnapshotListener { snapshot, e ->

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

        firebaseUtils.retrieveGeneralEntry().whereEqualTo("id", "$id").whereGreaterThanOrEqualTo("time", dateStart)
            .whereLessThanOrEqualTo("time", dateEnd).orderBy("time",
                Query.Direction.ASCENDING).addSnapshotListener { snapshot, e ->

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
        val dateEndTimeMillisPlusMinute=calendar.timeInMillis+60000
        val dateStartTime=Date(dateStartTimeMillis)
        //val dateEndTime=Date(dateEndTimeMillisPlusMinute)

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
        val dateEndTimeMillisPlusMinute=calendar.timeInMillis+60000
        val dateStartTime=Date(dateStartTimeMillis)
        //val dateEndTime=Date(dateEndTimeMillisPlusMinute)

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
        val dateEndTimeMillisPlusMinute=calendar.timeInMillis+60000
        val dateStartTime=Date(dateStartTimeMillis)
        //val dateEndTime=Date(dateEndTimeMillisPlusMinute)

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
        val dateEndTimeMillisPlusMinute=calendar.timeInMillis+60000
        val dateStartTime=Date(dateStartTimeMillis)
        //val dateEndTime=Date(dateEndTimeMillisPlusMinute)

        val retrieved=viewModel.retrieveGeneralEntryByDateDesc(id, dateStartTime,dateEndTime)

        return retrieved
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

    var allEntriesRetrieved : MutableLiveData<List<NonMoodEntry>> = MutableLiveData()
    // get realtime updates from firebase regarding saved moods
    fun retrieveAllGeneralEntries(id: String) : LiveData<List<NonMoodEntry>>{

        firebaseUtils.retrieveAllGeneralEntries("$id").orderBy("time").addSnapshotListener { snapshot, e ->

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
            //val time = mood.time?.toDate()
            //val formatedTime = SimpleDateFormat("HH:mm -dd/MM/yyyy").format(time)
            //val moodView = MoodView(entry, formatedTime)
            moodEntries.add(moodRating)
        }

        return moodEntries
    }


    /*fun accessRetrievedMoodRatingData(list: List<Mood>): ArrayList<Entry>{

        //val moodRatings= mutableListOf<Char>()
        val moodRatings= ArrayList<Entry>()
        var mood: Mood

        val retrievedMood = list as MutableList<Mood>
        moodRatings.clear()

        for (item in retrievedMood) {

            var temp=mutableListOf<Mood>()
            if (item.time?.toDate()!! < currentDate && item.time?.toDate()!! > dateStartTime){
                temp.add(item)

            }
            if(temp.size>1){
                var x=0
                for (tempItem in temp){

                    x+=tempItem.moodRating.toInt()
                }
                val average=x/temp.size
                moodRatings.add(Entry(7f, average.toFloat(),"7"))
            }else if(temp.size==1) {
                for (tempItem in temp) {
                    moodRatings.add(Entry(7f, tempItem.moodRating.toFloat(), "7"))
                }
            }else{

            }
            if(item.time?.toDate()!!<currentDate && item.time?.toDate()!! > dateCreator.getDateDaysAgo(dateStartTime,1)){





            /*mood = item
            val MoodRatingList=mood.moodRating
            //val MoodRatingList= ArrayList<Entry>()
            for (number in MoodRatingList){
                //moodRatings.add(number)*/


            }

        }
        Log.d("merlin", "$moodRatings")
        return moodRatings

    }*/
    /*fun accessRetrievedDaysMoodRatingData(list: List<Mood>, days:Int): ArrayList<Entry>{

        //val moodRatings= mutableListOf<Char>()
        val moodRatings= ArrayList<Entry>()
        var mood: Mood

        val retrievedMood = list as MutableList<Mood>
        moodRatings.clear()

        for (item in retrievedMood) {

            var temp=mutableListOf<Mood>()
            if (item.time?.toDate()!! < currentDate && item.time?.toDate()!! > dateStartTime){
                temp.add(item)

            }
            if(temp.size>1){
                var x=0
                for (tempItem in temp){

                    x+=tempItem.moodRating.toInt()
                }
                val average=x/temp.size
                moodRatings.add(Entry(7f, average.toFloat(),"7"))
            }else if(temp.size==1) {
                for (tempItem in temp) {
                    moodRatings.add(Entry(7f, tempItem.moodRating.toFloat(), "7"))
                }
            }else{

            }
            if(item.time?.toDate()!!<currentDate && item.time?.toDate()!! > dateCreator.getDateDaysAgo(dateStartTime,1)){





                /*mood = item
                val MoodRatingList=mood.moodRating
                //val MoodRatingList= ArrayList<Entry>()
                for (number in MoodRatingList){
                    //moodRatings.add(number)*/


            }

        }
        Log.d("merlin", "$moodRatings")
        return moodRatings

    }*/
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

        Log.d("merlin", "$moodKeywords")
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

        Log.d("merlin", "$keywords")
        return keywords

    }

    fun getCurrentUserName(id: String): Task<QuerySnapshot>{

           val query= firebaseUtils.getCurrentUserName(id).get()

        return query
    }


    fun retrieveMoodByKeyword(id: String, keyword: String):  LiveData<List<Mood>>{

        firebaseUtils.retrieveAllMoodEntries(id).whereArrayContains("keywords", keyword).addSnapshotListener { snapshot, e ->

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
        Log.d("sancho", "moo")

        return keywordMoodsRetrieved
    }

    fun retrieveGeneralByKeyword(id: String, keyword: String):  LiveData<List<NonMoodEntry>>{

        firebaseUtils.retrieveAllGeneralEntries(id).whereArrayContains("keywords", keyword).addSnapshotListener { snapshot, e ->

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
        Log.d("sancho", "moo")

        return keywordGeneralRetrieved
    }

    /*fun login(email: String, password: String,): Task<AuthResult> {
       val task= firebaseUtils.login(email, password)
        //Log.d("Derry", id)
        return task
    }*/

    //-------------------------------------------------------------

    /*fun retrieveMoodEntryByDate(dateStart: Date, dateEnd: Date): String {
        firebaseUtils.retrieveMoodEntryByDate(dateStart, dateEnd).observe(this, observer:Observer)
    }*/
    /*
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

    //-----------------------------------------------------------------

/*fun retrieve7DaysMoods(id: String): LiveData<List<Mood>>{

        val dateStartTimeMillis= dateEndTimeMillis-millisFor7Days
        val simpleDateStartTime=sdf.format(dateStartTimeMillis)
        val dateStartTime=dateCreator.getMilliseconds(simpleDateStartTime)
        val retrieved=viewModel.retrieveMoodEntryByDate(id, dateStartTime,currentDate)

        return retrieved
    }

    fun retrieve30DaysMoods(id: String): LiveData<List<Mood>>{

        val dateStartTimeMillis= dateEndTimeMillis-millisFor30Days
        val simpleDateStartTime=sdf.format(dateStartTimeMillis)
        val dateStartTime=dateCreator.getMilliseconds(simpleDateStartTime)
        val retrieved=viewModel.retrieveMoodEntryByDate(id, dateStartTime,currentDate)

        return retrieved
    }*/
    //--------------------------------------------------------------

    /*fun accessRetrievedKeywordData(list: List<Mood>): MutableList<String>{

        val moodKeywords= mutableListOf<String>()
        var mood: Mood
        val retrievedMood = list as MutableList<Mood>
        //Log.d("retrieved mood", "$retrievedMood")
        moodKeywords.clear()

        for (item in retrievedMood) {

            mood = item
            val keywordList=mood.keywords

            for (word in keywordList){
                moodKeywords.add(word)

            }

        }
        Log.d("merlin", "$moodKeywords")
        return moodKeywords

    }
    fun accessRetrievedTriggerData(list: List<Mood>): MutableList<String>{

        val moodKeywords= mutableListOf<String>()
        var mood: Mood
        val retrievedMood = list as MutableList<Mood>
        //Log.d("retrieved mood", "$retrievedMood")
        moodKeywords.clear()

        for (item in retrievedMood) {

            mood = item
            val keywordList=mood.triggers

            for (word in keywordList){
                moodKeywords.add(word)

            }

        }
        Log.d("merlin", "$moodKeywords")
        return moodKeywords

    }

    fun accessRetrievedPositivesData(list: List<Mood>): MutableList<String>{

        val moodKeywords= mutableListOf<String>()
        var mood: Mood
        val retrievedMood = list as MutableList<Mood>
        //Log.d("retrieved mood", "$retrievedMood")
        moodKeywords.clear()

        for (item in retrievedMood) {

            mood = item
            val keywordList=mood.positives

            for (word in keywordList){
                moodKeywords.add(word)

            }

        }
        Log.d("merlin", "$moodKeywords")
        return moodKeywords

    }*/
}