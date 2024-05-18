package com.example.matchscoresapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.matchscoresapp.databinding.ItemMatchBinding
import com.example.matchscoresapp.domain.model.Match

class MatchAdapter() : ListAdapter<Match, MatchAdapter.MatchViewHolder>(diffCallBack) {

    class MatchViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Match) {
            binding.textMatchStatus.text = item.matchAbbr
            binding.textHomeTeam.text = item.homeName
            binding.textAwayTeam.text = item.awayName
            binding.textMatchScore.text =
                "${item.homeMatchScore} - ${item.awayMatchScore}"
            binding.textHalfTimeScore.text =
                "${item.homeHalfScore} - ${item.awayMatchScore}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchBinding.inflate(inflater, parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object diffCallBack: DiffUtil.ItemCallback<Match>(){
        override fun areItemsTheSame(
            oldItem: Match,
            newItem: Match
        ): Boolean  = oldItem === newItem


        override fun areContentsTheSame(
            oldItem: Match,
            newItem: Match
        ): Boolean = oldItem.id == newItem.id
    }
}