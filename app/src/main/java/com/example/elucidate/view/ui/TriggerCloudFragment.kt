package com.example.elucidate.view.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.elucidate.databinding.FragmentTriggerCloudBinding
import com.example.elucidate.globalUser
import com.example.elucidate.viewModel
import com.mordred.wordcloud.WordCloud


class TriggerCloudFragment : Fragment() {

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

            val moodList= viewModel.accessRetrievedWordsData(it,"triggers")
            val keywordGroups=moodList.groupingBy { it }.eachCount().filter { it.value>0 }

            if(moodList.isEmpty()){
                Toast.makeText(activity, "No data yet", Toast.LENGTH_SHORT).show()
            }
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