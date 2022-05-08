package com.example.elucidate.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elucidate.R
import com.example.elucidate.databinding.FragmentRetreiveMoodEntriesBinding
import com.example.elucidate.databinding.FragmentRetrieveGeneralEntriesBinding
import com.example.elucidate.dto.Mood
import com.example.elucidate.dto.NonMoodEntry
import com.example.elucidate.globalUser
import com.example.elucidate.view.adapter.MoodAdapter
import com.example.elucidate.view.adapter.NonMoodAdapter
import com.example.elucidate.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [RetrieveGeneralEntriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RetrieveGeneralEntriesFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentRetrieveGeneralEntriesBinding.inflate(layoutInflater)
        val id = globalUser.id

        binding.btnGenSearch.setOnClickListener {

            val keyword= binding.etGenSearch.text.toString().trim()
            val retrieveKeywordMood= viewModel.retrieveGeneralByKeyword(id,keyword)
            retrieveKeywordMood.observe(viewLifecycleOwner, Observer { it->
                if(it.size>0){
                    populateRecyclerView(it, binding)
                }


            })
        }

        binding.btnGenViewAll.setOnClickListener {

            viewModel.retrieveAllGeneralEntries(id).observe(viewLifecycleOwner, Observer { it ->

                if(it.size>0){
                    populateRecyclerView(it, binding)
                }


            })
        }

        binding.btnGenToday.setOnClickListener {

            val retrieveCurrentDayMood= viewModel.retrieveCurrentDayGeneral(id)
            retrieveCurrentDayMood.observe(viewLifecycleOwner, Observer { it->
                if(it.size>0){
                    populateRecyclerView(it, binding)
                }


            })
        }

        binding.btnGen7Days.setOnClickListener {

            val retrieve7DaysMoods= viewModel.retrieveDayRangeGeneralDesc(id,7)
            retrieve7DaysMoods.observe(viewLifecycleOwner, Observer { it->
                if(it.size>0) {
                    populateRecyclerView(it, binding)
                }


            })
        }

        binding.btnGen30Days.setOnClickListener {

            //val retrieve30DaysMoods= viewModel.retrieve30DaysMoods(globalUser.id)
            val retrieve30DaysMoods= viewModel.retrieveDayRangeGeneralDesc(id, 30)
            retrieve30DaysMoods.observe(viewLifecycleOwner, Observer { it->

                if(it.size>0){
                    populateRecyclerView(it, binding)
                }


            })
        }

        binding.btnGenByDate.setOnClickListener {

            showDatePickerDialog(it)
        }

        return binding.root
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    fun populateRecyclerView(list: List<NonMoodEntry>, binding: FragmentRetrieveGeneralEntriesBinding) {

        val nonMoodEntries = viewModel.accessRetrievedGeneralListData(list)
        val adapter = NonMoodAdapter(nonMoodEntries)
        adapter.notifyDataSetChanged()
        binding.rvNonMoodEntries.adapter = adapter

        //https://www.codegrepper.com/code-examples/kotlin/android+recyclerview+not+scrolling+to+bottom
        binding.rvNonMoodEntries.scrollToPosition(adapter.itemCount - 1);
        binding.rvNonMoodEntries.layoutManager = LinearLayoutManager(activity)
    }
}