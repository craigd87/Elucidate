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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var auth: FirebaseAuth

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        /*val dateString="12-02-2022"
        val date1= Calendar.getInstance().set(2022,1,12,0,0,0)
        val date2= Calendar.getInstance().set(2022,1,13,0,0,0)
        val dateEx= Calendar.getInstance().timeInMillis*/

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

        //val finalDate =date.set(2022, 1, 12)
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
        /*FirebaseUtils().fireStoreDatabase.collection("userMoods")
            .add(hashMap)*/
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}