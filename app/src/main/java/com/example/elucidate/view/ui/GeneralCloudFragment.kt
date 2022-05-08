package com.example.elucidate.view.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentGeneralCloudBinding
import com.example.elucidate.globalUser
import com.example.elucidate.viewModel
import com.mordred.wordcloud.WordCloud

/**
 * A simple [Fragment] subclass.
 * Use the [GeneralCloudFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GeneralCloudFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentGeneralCloudBinding.inflate(layoutInflater)

        val id = globalUser.id

        viewModel.retrieveAllGeneralEntries(id).observe(viewLifecycleOwner, Observer { it ->

            if(it.size>0){
                val wordList= viewModel.accessRetrievedGeneralWordsData(it, "keywords")
                val keywordGroups=wordList.groupingBy { it }.eachCount().filter { it.value>0 }
                Log.d("Camelot", "$keywordGroups")

                val wd = WordCloud(keywordGroups, 250, 250, Color.BLACK, Color.WHITE)
                wd.setWordColorOpacityAuto(true)
                wd.setPaddingX(5)
                wd.setPaddingY(5)

                val generatedWordCloudBmp = wd.generate()

                binding.imageView.setImageBitmap(generatedWordCloudBmp)
            }


        })

        return binding.root
    }


}