package com.example.elucidate.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elucidate.dto.Mood
import com.example.elucidate.dto.MoodView
import com.example.elucidate.databinding.FragmentRetreiveMoodEntriesBinding
import com.example.elucidate.globalUser
import com.example.elucidate.view.adapter.MoodAdapter
import com.example.elucidate.viewModel
import java.text.SimpleDateFormat


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


        var retrievedMood= mutableListOf<Mood>()
        /*var moodEntries= mutableListOf(
            MoodView("hello", ""),
            MoodView("testing", ""),
            MoodView("my", ""),
            MoodView("Recycle", ""),
            MoodView("View", ""),
            MoodView("ENTRIES!", "")
        )*/
        var moodEntries= mutableListOf<MoodView>()

        //val adapter=MoodAdapter(moodEntries)

        binding.btnCheckDate.setOnClickListener {
            //binding.rvMoodEntries.adapter= adapter
            //binding.rvMoodEntries.layoutManager = LinearLayoutManager(activity)
            //val uid=viewModel.getCurrentUserId()

            viewModel.retrieveAllMoodEntries(globalUser.id).observe(viewLifecycleOwner, Observer { it ->
                Log.d("garfield", "$it")
                retrievedMood = it as MutableList<Mood>
                Log.d("retrieved mood", "$retrievedMood")
                var mood: Mood
                moodEntries.clear()
                for (item in retrievedMood) {
                    mood = item
                    /*Log.d("Mood details", mood.toString())
                    binding.textView2.text = mood.moodEntry
                    Log.d("mood received", mood.moodEntry)*/
                    val entry= mood.moodEntry
                    val time= mood.time?.toDate()
                    //val cal= Calendar.getInstance()
                    //cal.time=time
                    //val dayName=cal.get(Calendar.DAY_OF_WEEK_IN_MONTH).toString()
                    //val day=cal.get(Calendar.DAY_OF_MONTH).toString()
                    //val hour=cal.get(Calendar.HOUR_OF_DAY).toString()
                    val formatedTime= SimpleDateFormat("HH:mm -dd/MM/yyyy").format(time)
                    //val finalTime=dayName+" "+hour

                    val moodView= MoodView(entry, formatedTime)

                    moodEntries.add(moodView)




                }
                val adapter= MoodAdapter(moodEntries)
                adapter.notifyDataSetChanged()
                binding.rvMoodEntries.adapter= adapter


                //adapter.notifyDataSetChanged()
                //https://www.codegrepper.com/code-examples/kotlin/android+recyclerview+not+scrolling+to+bottom
                binding.rvMoodEntries.scrollToPosition(adapter.itemCount-1);
                binding.rvMoodEntries.layoutManager = LinearLayoutManager(activity)
                //moodEntries.clear()
                //adapter.notifyDataSetChanged()
                /*adapter.run {
                    setData(moodEntries)
                    notifyDataSetChanged()
                }*/


            })
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
        return binding.root
    }




}