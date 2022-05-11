package com.example.elucidate.view.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.elucidate.databinding.FragmentPositiveCloudBinding
import com.example.elucidate.globalUser
import com.example.elucidate.viewModel
import com.mordred.wordcloud.WordCloud

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 *
 * Using WordCloud by sirmordred
 * https://github.com/sirmordred/WordCloud
 */

class PositiveCloudFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentPositiveCloudBinding.inflate(layoutInflater)

        val id = globalUser.id
        viewModel.retrieveAllMoodEntries(id).observe(viewLifecycleOwner, Observer { it ->

            val moodList= viewModel.accessRetrievedWordsData(it, "positives")
            val keywordGroups=moodList.groupingBy { it }.eachCount().filter { it.value>0 }
            val wd = WordCloud(keywordGroups, 250, 250, Color.BLACK, Color.WHITE)

            if(moodList.isEmpty()){
                Toast.makeText(activity, "No data yet", Toast.LENGTH_SHORT).show()
            }
            wd.setWordColorOpacityAuto(true)
            wd.setPaddingX(5)
            wd.setPaddingY(5)

            val generatedWordCloudBmp = wd.generate()

            binding.imageViewPositives.setImageBitmap(generatedWordCloudBmp)

        })

        return binding.root
    }
}