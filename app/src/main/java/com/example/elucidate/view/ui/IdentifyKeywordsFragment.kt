package com.example.elucidate.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.elucidate.*
import com.example.elucidate.databinding.FragmentIdentifyKeywordsBinding
import com.example.elucidate.model.FirebaseUtils
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.*

class IdentifyKeywordsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentIdentifyKeywordsBinding.inflate(layoutInflater)

        //take date string and parse to obtain value in milliseconds from

        /*//val simpleDate1= "2022/03/29 00:00:00"
        //val simpleDate2= "2022/03/30 23:59:59"
        use above for searching specific dates
         */
        val formatCurrentDateWithoutTime= SimpleDateFormat("yyyy/MM/dd")

        //get current date without time
        val currentDateWithoutTime= formatCurrentDateWithoutTime.format(Date())

        //set values from start to end of current date as String and parse to get value in milliseconds
        val simpleCurrentDateStart= "$currentDateWithoutTime 00:00:00"
        val simpleCurrentDateEnd= "$currentDateWithoutTime 23:59:59"
        val sdf= SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val dateStartParse= sdf.parse(simpleCurrentDateStart)
        val dateEndParse= sdf.parse(simpleCurrentDateEnd)
        val dateStartMillis=dateStartParse.time
        val dateEndMillis=dateEndParse.time

        //create date objects from the milliseconds
        val finalDateStart= Date(dateStartMillis)
        val finalDateEnd= Date(dateEndMillis)

        //query Firestore to find mood entries from the current date
        val queryRef = FirebaseUtils().fireStoreDatabase.collection("userMoods")
        var stringForKeywords= globalMoodEntry
        //val stringForKeywords= viewModel.moodEntry.toString()
        val chosenKeywords= mutableListOf<String>()

        /*queryRef.whereGreaterThanOrEqualTo("time", finalDateStart).whereLessThanOrEqualTo("time", finalDateEnd)
            .orderBy("time", Query.Direction.DESCENDING).limit(1)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if(document!=null){
                        Log.d("exist", "DocumentSnapshot data: ${document.data}")
                        stringForKeywords= document.getString("moodEntry").toString()
                        Log.d("keywords", stringForKeywords)

         */
                        /* this retrieves one entry
                        binding.tvTestRead.text = mood
                         */
                        /*
                        for test purposes to show how tv can display entries, tv no longer exists
                        val text= TextView(activity)
                        text.text = mood
                        binding.textLayout.addView(text)
                         */

                        //var resultText= ""

                        //val delim15= "\n"
                        //val delimRegex= Regex()

                        val lower= stringForKeywords.lowercase()
                        Log.d("lowwwwer", lower)

                        val fileName= "stopwords.txt"
                        val stopWords = mutableListOf<String>()
                        val inputString = activity?.assets?.open(fileName)?.bufferedReader()?.useLines { lines -> lines.forEach { stopWords.add(it)} }
                        stopWords.forEach{
                            Log.i("Craig",  it)
                        }



                        /*var resultTestMut=resultText.toMutableList()
                        for(item in resultTestMut){
                            if(item.contentEquals(" ")){
                                resultTestMut.remove(item)
                            }
                        }
                        val length= resultTestMut.size*/
                        //Log.i("stringTest", "$printText")
                        //binding.textViewTestResult.text=printText.toString()
                        //binding.btnSplitTest.text=printText.toString()
                        //val chip= Chip(activity)
                        //chip.text="HELLO!"
                        //chip.setChipBackgroundColorResource(R.color.white)
                        //chip.setCloseIconVisible(true);
                        //binding.cgKeywords.addView(chip)
                        val keywordCreator= KeywordCreator()
                        val printText=keywordCreator.createKeywordList(lower, stopWords)

                        for (item in printText){
                            Log.d("chips", item)
                            //val chip= Chip(activity)
                            val chip=inflater.inflate(R.layout.filter_chip, binding.cgKeywords, false) as Chip
                            chip.text=item
                            chip.setChipBackgroundColorResource(R.color.white)
                            //chip.setCloseIconVisible(true);
                            binding.cgKeywords.addView(chip)
                        }

                        binding.btnCheckWords.setOnClickListener { view: View ->
                            val ids = binding.cgKeywords.checkedChipIds

                            for (id in ids) {
                                val wordSelection =
                                    binding.cgKeywords.findViewById<Chip>(id).text.toString()
                                chosenKeywords.add(binding.cgKeywords.findViewById<Chip>(id).text.toString())
                                globalKeywordsList.add(binding.cgKeywords.findViewById<Chip>(id).text.toString())
                                //viewModel.logKeywords(chosenKeywords)
                                Log.d("favourites", wordSelection)
                                Log.d("Mentry", globalMoodEntry)
                                Log.d("Mrating", globalMoodRating.toString())
                                Log.d("Mwords", "$globalKeywordsList")
                                //view?.findNavController()?.navigate(R.id.action_identifyKeywordsFragment_to_identifyTriggersFragment)
                                findNavController().safeNavigate(com.example.elucidate.view.ui.IdentifyKeywordsFragmentDirections.actionIdentifyKeywordsFragmentToIdentifyTriggersFragment())
                                //val action=IdentifyKeywordsFragmentDirections.actionIdentifyKeywordsFragmentToKeywordQualityFragment(chosenKeywords)
                            }
                        }




        return binding.root
    }

    fun NavController.safeNavigate(direction: NavDirections) {
        Log.d("navclick", "Click happened")
        currentDestination?.getAction(direction.actionId)?.run {
            Log.d("navclick", "Click Propagated")
            navigate(direction)
        }
    }
}