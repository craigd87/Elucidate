package com.example.elucidate.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elucidate.DateMillisCreator
import com.example.elucidate.dto.Mood
import com.example.elucidate.dto.MoodView
import com.example.elucidate.databinding.FragmentRetreiveMoodEntriesBinding
import com.example.elucidate.globalUser
import com.example.elucidate.view.adapter.MoodAdapter
import com.example.elucidate.viewModel
import java.text.SimpleDateFormat
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
        //var retrievedMood= mutableListOf<Mood>()
        //var moodEntries= mutableListOf<MoodView>()
        //var mood: Mood

        binding.btnViewAll.setOnClickListener {

            viewModel.retrieveAllMoodEntries(globalUser.id).observe(viewLifecycleOwner, Observer { it ->
                //Log.d("garfield", "$it")
                /*var retrievedMood = it as MutableList<Mood>
                //Log.d("retrieved mood", "$retrievedMood")
                moodEntries.clear()

                for (item in retrievedMood) {
                    mood = item

                    val entry= mood.moodEntry
                    val time= mood.time?.toDate()
                    val formatedTime= SimpleDateFormat("HH:mm -dd/MM/yyyy").format(time)

                    val moodView= MoodView(entry, formatedTime)

                    moodEntries.add(moodView)

                }*/
                val moodEntries= viewModel.accessRetrievedMoodListData(it)

                val adapter= MoodAdapter(moodEntries)
                adapter.notifyDataSetChanged()
                binding.rvMoodEntries.adapter= adapter

                //https://www.codegrepper.com/code-examples/kotlin/android+recyclerview+not+scrolling+to+bottom
                binding.rvMoodEntries.scrollToPosition(adapter.itemCount-1);
                binding.rvMoodEntries.layoutManager = LinearLayoutManager(activity)


            })
        }

        binding.btnToday.setOnClickListener {
            /*val date= Calendar.getInstance()
            val currentDate = date.time
            val formatedDate = SimpleDateFormat("yyyy/MM/dd").format(currentDate)
            val simpleDateStart="$formatedDate 00:00:00"
            val simpleDateEnd="$formatedDate 23:59:59"
            val dateCreator= DateMillisCreator()
            val dateStartTime=dateCreator.getMilliseconds(simpleDateStart)
            val dateEndTime=dateCreator.getMilliseconds(simpleDateEnd)*/
            //val moodText= viewModel.retrieveMoodEntryByDate(dateStartTime,dateEndTime)
            //binding.textView2.text=moodText.toString()
            //viewModel.retrieveMoodEntryByDate(globalUser.id, dateStartTime,dateEndTime).observe(viewLifecycleOwner, Observer { it->
            val retrieveCurrentDayMood= viewModel.retrieveCurrentDayMood(globalUser.id)
            retrieveCurrentDayMood.observe(viewLifecycleOwner, Observer { it->
            val moodEntries= viewModel.accessRetrievedMoodListData(it)

                val adapter= MoodAdapter(moodEntries)
                adapter.notifyDataSetChanged()
                binding.rvMoodEntries.adapter= adapter

                //https://www.codegrepper.com/code-examples/kotlin/android+recyclerview+not+scrolling+to+bottom
                binding.rvMoodEntries.scrollToPosition(adapter.itemCount-1);
                binding.rvMoodEntries.layoutManager = LinearLayoutManager(activity)

        })
    }

        binding.btn7Days.setOnClickListener {

            val retrieve7DaysMoods= viewModel.retrieve7DaysMoods(globalUser.id)
            retrieve7DaysMoods.observe(viewLifecycleOwner, Observer { it->
                val moodEntries= viewModel.accessRetrievedMoodListData(it)

                val adapter= MoodAdapter(moodEntries)
                adapter.notifyDataSetChanged()
                binding.rvMoodEntries.adapter= adapter

                //https://www.codegrepper.com/code-examples/kotlin/android+recyclerview+not+scrolling+to+bottom
                binding.rvMoodEntries.scrollToPosition(adapter.itemCount-1);
                binding.rvMoodEntries.layoutManager = LinearLayoutManager(activity)

            })
        }

        binding.btn30Days.setOnClickListener {

            val retrieve30DaysMoods= viewModel.retrieve30DaysMoods(globalUser.id)
            retrieve30DaysMoods.observe(viewLifecycleOwner, Observer { it->
                val moodEntries= viewModel.accessRetrievedMoodListData(it)

                val adapter= MoodAdapter(moodEntries)
                adapter.notifyDataSetChanged()
                binding.rvMoodEntries.adapter= adapter

                //https://www.codegrepper.com/code-examples/kotlin/android+recyclerview+not+scrolling+to+bottom
                binding.rvMoodEntries.scrollToPosition(adapter.itemCount-1);
                binding.rvMoodEntries.layoutManager = LinearLayoutManager(activity)

            })
        }

        


        return binding.root
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



}