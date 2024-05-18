package com.example.matchscoresapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchscoresapp.databinding.ItemLeagueBinding
import com.example.matchscoresapp.domain.model.League

class LeagueAdapter(): ListAdapter<League, LeagueAdapter.LeagueViewHolder>(diffCallBack) {

    class LeagueViewHolder(private val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: League) {
            Glide.with(binding.root.context).load(item.league.url).into(binding.leagueImage)
            binding.leagueNameText.text = item.league.name
            val adapter = MatchAdapter()
            binding.matchListView.adapter = adapter
            adapter.submitList(item.list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLeagueBinding.inflate(inflater, parent, false)
        return LeagueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object diffCallBack: DiffUtil.ItemCallback<League>(){
        override fun areItemsTheSame(
            oldItem: League,
            newItem: League
        ): Boolean  = oldItem === newItem


        override fun areContentsTheSame(
            oldItem: League,
            newItem: League
        ): Boolean = oldItem.league.id == newItem.league.id
    }
}