package com.example.elucidate.view.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.elucidate.CloudFragment
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentCloudBinding
import com.example.elucidate.databinding.FragmentTriggerCloudBinding
import com.example.elucidate.dto.Mood
import com.example.elucidate.globalUser
import com.example.elucidate.viewModel
import com.mordred.wordcloud.WordCloud


class TriggerCloudFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentTriggerCloudBinding.inflate(layoutInflater)

        val id = globalUser.id

        viewModel.retrieveAllMoodEntries(id).observe(viewLifecycleOwner, Observer { it ->

            val moodList= viewModel.accessRetrievedTriggerData(it)
            val keywordGroups=moodList.groupingBy { it }.eachCount().filter { it.value>0 }
            Log.d("Camelot", "$keywordGroups")

            val wd = WordCloud(keywordGroups, 250, 250, Color.BLACK, Color.WHITE)
            wd.setWordColorOpacityAuto(true)
            wd.setPaddingX(5)
            wd.setPaddingY(5)

            val generatedWordCloudBmp = wd.generate()

            binding.imageViewTrigger.setImageBitmap(generatedWordCloudBmp)

        })




        return binding.root
    }


}