package com.example.matchscoresapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.matchscoresapp.core.getAwayName
import com.example.matchscoresapp.core.setBackgroundColorAndDividerVisibility
import com.example.matchscoresapp.core.getHomeName
import com.example.matchscoresapp.databinding.ItemMatchBinding
import com.example.matchscoresapp.domain.model.Match

class MatchAdapter(private val onClick: (match: Match) -> Unit) :
    ListAdapter<Match, MatchAdapter.MatchViewHolder>(diffCallBack) {

    class MatchViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(
            item: Match,
            onClick: (match: Match) -> Unit,
            bgAndDividerVisibility: Pair<Int, Boolean>
        ) {
            binding.container.background =
                binding.root.context.getDrawable(bgAndDividerVisibility.first)
            binding.divider.isVisible = bgAndDividerVisibility.second
            binding.textMatchStatus.text = item.matchAbbr
            binding.textHomeTeam.text = item.getHomeName()
            binding.textAwayTeam.text = item.getAwayName()
            binding.textMatchScore.text =
                "${item.homeMatchScore} - ${item.awayMatchScore}"
            binding.textHalfTimeScore.text =
                "${item.homeHalfScore} - ${item.awayMatchScore}"

            binding.root.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchBinding.inflate(inflater, parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {

        holder.bind(
            getItem(position),
            onClick,
            currentList.setBackgroundColorAndDividerVisibility(position)
        )
    }

    object diffCallBack : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(
            oldItem: Match,
            newItem: Match
        ): Boolean = oldItem === newItem


        override fun areContentsTheSame(
            oldItem: Match,
            newItem: Match
        ): Boolean = oldItem.id == newItem.id
    }
}