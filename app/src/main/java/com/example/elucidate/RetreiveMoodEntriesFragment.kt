package com.example.elucidate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elucidate.databinding.FragmentRetreiveMoodEntriesBinding


class RetreiveMoodEntriesFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRetreiveMoodEntriesBinding.inflate(layoutInflater)


        var retrievedMood= mutableListOf<Mood>()
        var moodEntries= mutableListOf(
            MoodView("hello"),
            MoodView("testing"),
            MoodView("my"),
            MoodView("Recycle"),
            MoodView("View"),
            MoodView("ENTRIES!")
        )

        val adapter=MoodAdapter(moodEntries)
        binding.btnCheckDate.setOnClickListener {
            binding.rvMoodEntries.adapter= adapter
            binding.rvMoodEntries.layoutManager = LinearLayoutManager(activity)
        }

        /*binding.btnCheckDate.setOnClickListener {
            val date= binding.etEnterDate.text.toString()
            val simpleDateStart="$date 00:00:00"
            val simpleDateEnd="$date 23:59:59"
            val dateCreator= DateMillisCreator()
            val dateStartTime=dateCreator.getMilliseconds(simpleDateStart)
            val dateEndTime=dateCreator.getMilliseconds(simpleDateEnd)
            //val moodText= viewModel.retrieveMoodEntryByDate(dateStartTime,dateEndTime)
            //binding.textView2.text=moodText.toString()
            viewModel.retrieveMoodEntryByDate(dateStartTime,dateEndTime).observe(viewLifecycleOwner, Observer { it->
                retrievedMood = it as MutableList<Mood>
                Log.d("retrieved mood", "$retrievedMood")
                var mood:Mood
                for(item in retrievedMood){
                    mood=item
                    Log.d("Mood details", mood.toString())
                    binding.textView2.text=mood.moodEntry
                    Log.d("mood received",mood.moodEntry)
                }

        })





    }*/
        return binding.root
    }


}