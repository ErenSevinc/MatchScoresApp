package com.example.matchscoresapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.example.matchscoresapp.databinding.ItemLeagueBinding
import com.example.matchscoresapp.domain.model.League
import com.example.matchscoresapp.domain.model.Match

class LeagueAdapter(private val onClick: (match: Match) -> Unit): ListAdapter<League, LeagueAdapter.LeagueViewHolder>(diffCallBack) {

    class LeagueViewHolder(private val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: League, onClick: (match: Match) -> Unit) {
            Glide.with(binding.root.context).load(item.league.url).into(binding.leagueImage)
            binding.leagueNameText.text = item.league.name

            setupMatchAdapter(onClick, item.list)
        }
        
        
        private fun setupMatchAdapter(onClick: (match: Match) -> Unit, list: List<Match>) {
            with(binding) {
                val adapter = MatchAdapter(onClick)
                val layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)

                matchListView.layoutManager = layoutManager
                matchListView.adapter = adapter
                adapter.submitList(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLeagueBinding.inflate(inflater, parent, false)
        return LeagueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
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