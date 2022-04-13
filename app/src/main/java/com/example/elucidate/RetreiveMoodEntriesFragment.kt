package com.example.elucidate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val date= binding.etEnterDate.text.toString()
        val simpleDateStart=date+" 00:00:00"
        val simpleDateEnd=date+" 23:59:59"
        val dateCreator= DateMillisCreator()
        val dateStartTime=dateCreator.getMilliseconds(simpleDateStart)
        val dateEndTime=dateCreator.getMilliseconds(simpleDateEnd)

        binding.btnCheckDate.setOnClickListener {
            val moodText= viewModel.retrieveMoodEntryByDate(dateStartTime,dateEndTime)
            binding.textView2.text=moodText

        }

        return binding.root
    }


}