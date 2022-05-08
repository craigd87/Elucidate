package com.example.elucidate.view.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.elucidate.databinding.FragmentPositiveCloudBinding
import com.example.elucidate.globalUser
import com.example.elucidate.viewModel
import com.mordred.wordcloud.WordCloud

/**
 * A simple [Fragment] subclass.
 * Use the [PositiveCloudFragment.newInstance] factory method to
 * create an instance of this fragment.
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

            wd.setWordColorOpacityAuto(true)
            wd.setPaddingX(5)
            wd.setPaddingY(5)

            val generatedWordCloudBmp = wd.generate()

            binding.imageViewPositives.setImageBitmap(generatedWordCloudBmp)

        })

        return binding.root
    }
}