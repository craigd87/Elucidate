package com.example.elucidate

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController

import com.example.elucidate.databinding.FragmentDashboardBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.util.logging.Level.parse
import kotlin.collections.HashMap


private lateinit var auth: FirebaseAuth

class DashboardFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDashboardBinding.inflate(layoutInflater)

        auth = Firebase.auth
        val user= auth.currentUser

        val uName = user!!.displayName
        val id = user.uid
        var name = ""
        var age = ""


        //take date string and parse to obtain value in milliseconds from
        val simpleDate1= "2022/02/12 00:00:00"
        val simpleDate2= "2022/02/12 23:59:59"
        val sdf= SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date1Parse= sdf.parse(simpleDate1)
        val date2Parse= sdf.parse(simpleDate2)
        val date1Millis=date1Parse.time
        val date2Millis=date2Parse.time

        //create date objects from the milliseconds
        val finalDate1= Date(date1Millis)
        val finalDate2= Date(date2Millis)

        var moodRating=""




            /*val queryRef = FirebaseUtils().fireStoreDatabase.collection("users")
            queryRef.whereEqualTo("name", "Monica")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d("exist", "DocumentSnapshot data: ${document.data}")
                        age = document.getString("age").toString()
                        binding.textDashWelcome.text = "Hi " + age
                    }

                }*/

           val queryRef = FirebaseUtils().fireStoreDatabase.collection("userMoods")

           queryRef.whereGreaterThanOrEqualTo("time", finalDate1).whereLessThan("time", finalDate2)
               .get()
               .addOnSuccessListener { documents ->
                   for (document in documents) {
                       if(document!=null){
                       Log.d("exist", "DocumentSnapshot data: ${document.data}")
                       //moodRating = document.getString("moodRating").toString()
                           moodRating= document.getString("moodRating").toString()
                           binding.textDashWelcome.text = "Hi " + moodRating
                       }else{
                           binding.textDashWelcome.text = "NULL"
                       }

                   }

               }

       /*} catch (e:Exception){
           binding.textDashWelcome.text = "eXCEPTION"
       }*/
        //binding.textDashWelcome.text="$dateMillis1 then $dateMillis2"




            val mood = binding.editTextMood.text
            val uid = user?.uid
            val moodDetails = hashMapOf<String, Any>()

            binding.btnLogMood.setOnClickListener { view: View ->

                logMood(moodDetails, uid, "$mood")

            }

            binding.btnDashLogOut.setOnClickListener {
                Firebase.auth.signOut()
                activity?.finish()
            }



        return binding.root
    }

    fun logMood(moodDetails: HashMap<String, Any>, uid: String, mood: String){

        moodDetails.put("id", uid)
        moodDetails.put("mood", "$mood")

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
                Log.w(TAG, "Error adding document $exception")
                Toast.makeText(
                    context, "Error adding document $exception",
                    Toast.LENGTH_LONG
                ).show()
            }
        //view?.findNavController()?.navigate(R.id.action_dashboardFragment_to_moodRatingFragment)
    }


}