package com.example.elucidate

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.elucidate.databinding.FragmentMoodEntryBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap


//private lateinit var auth: FirebaseAuth
class MoodEntryFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoodEntryBinding.inflate(layoutInflater)
        /*val bundle = arguments
        if (bundle==null){
            Log.e("Confirmation", "Mood entry not logged")
            return null
        }*/
        //auth = Firebase.auth
        //val user= auth.currentUser

        //val uid = user?.uid.toString()
        val user=viewModel.getCurrentUserId()
        val uid= viewModel.getCurrentUserId()
        val moodDetails = hashMapOf<Any, Any>()

        /*val queryRef = FirebaseUtils().fireStoreDatabase.collection("users")
        queryRef.whereEqualTo("id", "$uid")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("exist", "DocumentSnapshot data: ${document.data}")
                    val name = document.getString("name").toString()
                    binding.tvEnterMood.text = "Hi " + name+"! please enter details about how you are feeling"
                }

            }*/
        binding.tvEnterMood.text = "Hi " + globalUser.name+"! please enter details about how you are feeling"

        /*binding.sbRateMood.setOnClickListener{view : View ->
            moodRating= binding.sbRateMood.progress
            //logMood(moodDetails, "$uid", moodRating)
            /*view.findNavController().navigate(R.id.action_moodRatingFragment_to_moodEntry)*/
        }*/

        binding.btnSubmitMood.setOnClickListener { view: View ->
            val moodRating=binding.sbRateMood.progress
            val mood = binding.etEnterMood.text.toString()
            //Mood mood= new Mood()
            globalMoodEntry=binding.etEnterMood.text.toString()
            globalMoodRating=binding.sbRateMood.progress

            //viewModel.enterMood(mood)
            //viewModel.enterMoodRating("$moodRating")
            view?.findNavController()?.navigate(R.id.action_moodEntryFragment_to_identifyKeywordsFragment)
            //logMood(moodDetails, uid, mood, moodRating)

        }


        return binding.root
    }
    fun logMood(moodDetails: HashMap<Any, Any>, uid: String, mood: String, moodRating: Int){
        /*FirebaseUtils().fireStoreDatabase.collection("userMoods")
            .add(hashMap)*/
        moodDetails.put("id", uid)
        moodDetails.put("moodEntry", mood)
        moodDetails.put("moodRating", "$moodRating")
        //moodRatingDetails.put("time", SimpleDateFormat("dd/mm/yyy hh:mm:ss").format(Date()))
        moodDetails.put("time", Timestamp(Date()))

        val userMoods = FirebaseUtils().fireStoreDatabase.collection("userMoods")
        userMoods.document().set(moodDetails)
            .addOnSuccessListener {
                //Log.d(TAG, "Added document with ID ${it.id}")
                Toast.makeText(
                    context, "SUCCESS!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error adding document $exception")
                Toast.makeText(
                    context, "Error adding document $exception",
                    Toast.LENGTH_LONG
                ).show()
            }
        view?.findNavController()?.navigate(R.id.action_moodEntryFragment_to_identifyKeywordsFragment)
    }


}