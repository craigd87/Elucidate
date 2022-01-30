package com.example.elucidate

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
import com.example.elucidate.databinding.ActivityDashboardBinding
import com.example.elucidate.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.ktx.Firebase

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

    //var mood= ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dashboard, container, false)
        //val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,
          //  R.layout.fragment_dashboard,container,false)
        //return binding.root
        val binding = FragmentDashboardBinding.inflate(layoutInflater)
        auth= Firebase.auth
        val user = auth.currentUser
        val uName= user!!.displayName
        val id= user.uid

        val docRef= FirebaseUtils().fireStoreDatabase.collection("users")

        val docData=docRef.whereEqualTo("id", "$id").get().toString()



        if (user != null) {
            binding.textDashWelcome.text="Hi $docData"
        }else{
            binding.textDashWelcome.text="null"
        }
        val mood=binding.editTextMood.text
        val uid= user?.uid
        val hashMap = hashMapOf<String, Any>(
            "id" to "$uid",
            "mood" to "$mood"

        )

        binding.btnLogMood.setOnClickListener{view : View ->
            //var mood= binding.editTextLogMood.text
            //view.findNavController().navigate(R.id.action_dashboardFragment_to_moodRatingFragment)

            FirebaseUtils().fireStoreDatabase.collection("userMoods")
                .add(hashMap)
                .addOnSuccessListener {
                    Log.d(TAG, "Added document with ID ${it.id}")
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
        }
        binding.btnDashLogOut.setOnClickListener{
            Firebase.auth.signOut()
            activity?.finish()
        }

        return binding.root
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