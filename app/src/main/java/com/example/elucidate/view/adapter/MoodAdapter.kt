package com.example.elucidate.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elucidate.dto.MoodView
import com.example.elucidate.databinding.ItemMoodEntryBinding

/*
help from https://stackoverflow.com/questions/60423596/how-to-use-viewbinding-in-a-recyclerview-adapter

How to use ViewBinding in a RecyclerView.Adapter?
answered Feb 27, 2020 at 0:06
by
A1m
 */

class MoodAdapter(var moodViews: MutableList<MoodView>) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    //inner class MoodViewHolder(moodView: View): RecyclerView.ViewHolder(moodView){
    inner class MoodViewHolder(val binding: ItemMoodEntryBinding): RecyclerView.ViewHolder(binding.root){
        //var moodTextView: TextView = view.findViewById(R.id.tvMood)
        //var moodTextView: TextView = ItemMoodEntryBinding.inflate(layoutInflater)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mood_entry, parent, false)
        //return MoodViewHolder(view)
        //val moodBinding = ItemMoodEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //return MoodViewHolder(moodBinding.tvMood)
        //return MoodViewHolder(moodBinding)
        val binding = ItemMoodEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoodViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return moodViews.size
    }
    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        //val binding=ItemMoodEntryBinding.inflate(layoutInflater)
        //val moodView: MoodView = moodViews[position]
        //holder.bind(moodView)
        holder.binding.apply {
            tvMood.text = moodViews[position].moodEntry+"\n"+moodViews[position].time
        }

        }




}