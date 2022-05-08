package com.example.elucidate.view.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elucidate.databinding.FragmentRetreiveMoodEntriesBinding
import com.example.elucidate.dto.Mood
import com.example.elucidate.globalUser
import com.example.elucidate.view.adapter.MoodAdapter
import com.example.elucidate.viewModel
import java.util.*


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

        val id = globalUser.id

        binding.btnSearch.setOnClickListener {

            val keyword= binding.etSearch.text.toString().trim()
            val retrieveKeywordMood= viewModel.retrieveMoodByKeyword(id,keyword)
            retrieveKeywordMood.observe(viewLifecycleOwner, Observer { it->

                populateRecyclerView(it, binding)

            })
        }

        binding.btnViewAll.setOnClickListener {

            viewModel.retrieveAllMoodEntries(id).observe(viewLifecycleOwner, Observer { it ->

                populateRecyclerView(it, binding)

            })
        }

        binding.btnToday.setOnClickListener {

            val retrieveCurrentDayMood= viewModel.retrieveCurrentDayMood(id)
            retrieveCurrentDayMood.observe(viewLifecycleOwner, Observer { it->

                populateRecyclerView(it, binding)

             })
        }

        binding.btn7Days.setOnClickListener {

            val retrieve7DaysMoods= viewModel.retrieveDayRangeMoodsDesc(id,7)
            retrieve7DaysMoods.observe(viewLifecycleOwner, Observer { it->

                populateRecyclerView(it, binding)

            })
        }

        binding.btn30Days.setOnClickListener {

            //val retrieve30DaysMoods= viewModel.retrieve30DaysMoods(globalUser.id)
            val retrieve30DaysMoods= viewModel.retrieveDayRangeMoodsDesc(id, 30)
            retrieve30DaysMoods.observe(viewLifecycleOwner, Observer { it->

                populateRecyclerView(it, binding)

            })
        }

        binding.btnByDate.setOnClickListener {

            var cal= Calendar.getInstance()
            showDatePickerDialog(it)
            val dateSetListener=DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }


        }


        return binding.root
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    fun populateRecyclerView(list: List<Mood>, binding: FragmentRetreiveMoodEntriesBinding) {

        val moodEntries = viewModel.accessRetrievedMoodListData(list)
        val adapter = MoodAdapter(moodEntries)
        adapter.notifyDataSetChanged()
        binding.rvMoodEntries.adapter = adapter

        //https://www.codegrepper.com/code-examples/kotlin/android+recyclerview+not+scrolling+to+bottom
        binding.rvMoodEntries.scrollToPosition(adapter.itemCount - 1);
        binding.rvMoodEntries.layoutManager = LinearLayoutManager(activity)
    }


}