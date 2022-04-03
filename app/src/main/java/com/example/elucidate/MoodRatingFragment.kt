package com.example.elucidate

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.elucidate.databinding.FragmentDashboardBinding
import com.example.elucidate.databinding.FragmentMoodRatingBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var auth: FirebaseAuth

/**
 * A simple [Fragment] subclass.
 * Use the [MoodRatingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoodRatingFragment : Fragment() {
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
        // Inflate the layout for this fragment
        val binding = FragmentMoodRatingBinding.inflate(layoutInflater)
        auth= Firebase.auth
        val user = auth.currentUser
        val uid= user?.uid
        val moodRatingDetails = hashMapOf<Any, Any>()


        binding.btnRateMood.setOnClickListener{view : View ->
            val moodRating= binding.seekBar.progress
            //globalMoodRating=binding.seekBar.progress
            logMood(moodRatingDetails, "$uid", moodRating)
            /*view.findNavController().navigate(R.id.action_moodRatingFragment_to_moodEntry)*/
        }

        return binding.root
    }

    fun logMood(moodRatingDetails: HashMap<Any, Any>, uid: String, moodRating: Int){
        /*FirebaseUtils().fireStoreDatabase.collection("userMoods")
            .add(hashMap)*/
        moodRatingDetails.put("id", uid)
        moodRatingDetails.put("moodRating", "$moodRating")
        //moodRatingDetails.put("time", SimpleDateFormat("dd/mm/yyy hh:mm:ss").format(Date()))
        moodRatingDetails.put("time", Timestamp(Date()))

        val userMoods = FirebaseUtils().fireStoreDatabase.collection("userMoods")
        userMoods.document().set(moodRatingDetails)
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
         * @return A new instance of fragment MoodRatingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MoodRatingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}