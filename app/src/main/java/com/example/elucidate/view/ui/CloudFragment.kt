
/*The MIT License (MIT)

Copyright (c) 2019 Oğuzhan Yiğit

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.*/
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
import com.example.elucidate.databinding.FragmentCloudBinding
import com.example.elucidate.globalUser
import com.example.elucidate.viewModel
import com.mordred.wordcloud.WordCloud


/**
 * A [Fragment] to create and display a weighted cloud bitmap image
 * based on selection and frequency of user [Mood] keywords.
 */
class CloudFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentCloudBinding.inflate(layoutInflater)

        val id = globalUser.id

        viewModel.retrieveAllMoodEntries(id).observe(viewLifecycleOwner, Observer { it ->

            val moodList= viewModel.accessRetrievedWordsData(it, "keywords")
            val keywordGroups=moodList.groupingBy { it }.eachCount().filter { it.value>0 }
            Log.d("Camelot", "$keywordGroups")

            if(keywordGroups.isEmpty()) {
                Toast.makeText(activity, "No data yet", Toast.LENGTH_SHORT).show()

            }

                val wd = WordCloud(keywordGroups, 250, 250, Color.BLACK, Color.WHITE)
            wd.setWordColorOpacityAuto(true)
            wd.setPaddingX(5)
            wd.setPaddingY(5)

            val generatedWordCloudBmp = wd.generate()

            binding.imageView.setImageBitmap(generatedWordCloudBmp)

        })

        return binding.root
    }

}