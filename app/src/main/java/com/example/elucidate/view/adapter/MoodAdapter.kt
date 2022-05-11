package com.example.elucidate.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elucidate.dto.MoodView
import com.example.elucidate.databinding.ItemMoodEntryBinding

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */

/*
help from https://stackoverflow.com/questions/60423596/how-to-use-viewbinding-in-a-recyclerview-adapter

How to use ViewBinding in a RecyclerView.Adapter?
answered Feb 27, 2020 at 0:06
by
A1m
 */

/**
 * An adapter class to populate the RecyclerView with [MoodView]s
 */
class MoodAdapter(var moodViews: MutableList<MoodView>) : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {


    inner class MoodViewHolder(val binding: ItemMoodEntryBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {

        val binding = ItemMoodEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoodViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return moodViews.size
    }
    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {

        holder.binding.apply {
            tvMood.text = moodViews[position].moodEntry+"\n"+moodViews[position].time
        }

        }


}