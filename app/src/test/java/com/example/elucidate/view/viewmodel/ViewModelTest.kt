package com.example.elucidate.view.viewmodel

import com.example.elucidate.dto.User
import com.example.elucidate.model.FirebaseUtils
import org.junit.Assert.*

import org.junit.Test

class ViewModelTest {

    val firebaseUtils=FirebaseUtils()
    @Test
    fun saveUserDetailsToFirestore() {
        var userSaved:Boolean=false
        val user= User("sampleId","sampleName")
        val task=firebaseUtils.saveUserDetails(user)
        if(task.isSuccessful){
            userSaved=true
        }else{
            userSaved=false
        }
        assertEquals(true, userSaved)

    }

    @Test
    fun saveMoodDetailsToFirestore() {
    }

    @Test
    fun saveGeneralEntryDetailsToFirestore() {
    }

    @Test
    fun signup() {
    }

    @Test
    fun retrieveUser() {
    }

    @Test
    fun retrieveMoodEntryByDateDesc() {
    }

    @Test
    fun retrieveGeneralEntryByDateDesc() {
    }

    @Test
    fun retrieveMoodEntryByDateAsc() {
    }

    @Test
    fun retrieveGeneralEntryByDateAsc() {
    }

    @Test
    fun retrieveCurrentDayMood() {
    }

    @Test
    fun retrieveCurrentDayGeneral() {
    }

    @Test
    fun retrieveDayRangeMoodsAsc() {
    }

    @Test
    fun retrieveDayRangeGeneralAsc() {
    }

    @Test
    fun retrieveDayRangeMoodsDesc() {
    }

    @Test
    fun retrieveDayRangeGeneralDesc() {
    }

    @Test
    fun retrieveAllMoodEntries() {
    }

    @Test
    fun retrieveAllGeneralEntries() {
    }

    @Test
    fun accessRetrievedMoodListData() {
    }

    @Test
    fun accessRetrievedGeneralListData() {
    }

    @Test
    fun accessRetrievedMoodRatingData() {
    }

    @Test
    fun accessRetrievedWordsData() {
    }

    @Test
    fun accessRetrievedGeneralWordsData() {
    }

    @Test
    fun getCurrentUserName() {
    }

    @Test
    fun retrieveMoodByKeyword() {
    }

    @Test
    fun retrieveGeneralByKeyword() {
    }
}