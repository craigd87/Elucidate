package com.example.elucidate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.elucidate.databinding.FragmentDashboardBinding
import com.example.elucidate.databinding.FragmentMoodEntryBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MoodEntry.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoodEntry : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mood_entry, container, false)
        //val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,
          //  R.layout.fragment_mood_entry,container,false)
        //return binding.root
        val binding = FragmentMoodEntryBinding.inflate(layoutInflater)
        val bundle = arguments
        if (bundle==null){
            Log.e("Confirmation", "Mood entry not logged")
            return null
        }


        return binding.root
    }

}