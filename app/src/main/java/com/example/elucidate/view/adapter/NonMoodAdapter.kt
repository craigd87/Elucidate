package com.example.elucidate.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elucidate.databinding.ItemGeneralEntryBinding
import com.example.elucidate.databinding.ItemMoodEntryBinding
import com.example.elucidate.dto.MoodView
import com.example.elucidate.dto.NonMoodView

/**
 * Large amounts of research taken from:
 * https://developer.android.com/
 * https://developer.android.com/codelabs
 * and the videos of Philipp Lackner
 * https://www.youtube.com/c/PhilippLackner
 */

/**
 * An adapter class to populate the RecyclerView with [NonMoodView]s
 */
class NonMoodAdapter(var nonMoodViews: MutableList<NonMoodView>): RecyclerView.Adapter<NonMoodAdapter.NonMoodViewHolder>() {


    inner class NonMoodViewHolder(val binding: ItemGeneralEntryBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NonMoodViewHolder {

        val binding = ItemGeneralEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NonMoodViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return nonMoodViews.size
    }
    override fun onBindViewHolder(holder: NonMoodViewHolder, position: Int) {

        holder.binding.apply {
            tvGeneral.text = nonMoodViews[position].entry+"\n"+nonMoodViews[position].time
        }

    }



}