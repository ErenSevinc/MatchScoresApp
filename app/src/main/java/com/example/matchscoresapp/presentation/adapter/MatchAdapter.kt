package com.example.matchscoresapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.matchscoresapp.core.getAwayName
import com.example.matchscoresapp.core.getHomeName
import com.example.matchscoresapp.core.isFavMatch
import com.example.matchscoresapp.core.setBackgroundColorAndDividerVisibility
import com.example.matchscoresapp.databinding.ItemMatchBinding
import com.example.matchscoresapp.domain.model.Match

class MatchAdapter(private val onClick: (match: Match) -> Unit, private val onFavClick: (match: Match) -> Unit) :
    ListAdapter<Match, MatchAdapter.MatchViewHolder>(diffCallBack) {

    class MatchViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(
            item: Match,
            onClick: (match: Match) -> Unit,
            onFavClick: (match: Match) -> Unit,
            bgAndDividerVisibility: Pair<Int, Boolean>
        ) {
            with(binding) {
                container.background = root.context.getDrawable(bgAndDividerVisibility.first)
                divider.isVisible = bgAndDividerVisibility.second
                textMatchStatus.text = item.matchAbbr
                textHomeTeam.text = item.getHomeName()
                textAwayTeam.text = item.getAwayName()
                textMatchScore.text = "${item.homeMatchScore} - ${item.awayMatchScore}"
                buttonFavourite.setBackgroundResource(item.isFavourite.isFavMatch())

                root.setOnClickListener {
                    onClick.invoke(item)
                }
                buttonFavourite.setOnClickListener {
                    onFavClick.invoke(item)
                }
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
            onFavClick,
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
        ): Boolean = oldItem.matchId == newItem.matchId
    }
}