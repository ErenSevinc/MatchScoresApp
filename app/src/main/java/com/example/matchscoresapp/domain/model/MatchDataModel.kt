package com.example.matchscoresapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class League(
    val league: LeagueItem,
    val list: List<Match>
): Parcelable

@Parcelize
data class LeagueItem(
    val id: Long,
    val name: String,
    val url: String
): Parcelable

@Parcelize
data class Match(
    val id: Long,
    val date: Long,
    val homeName: String,
    val homeSubName: String,
    val awayName: String,
    val awaySubName: String,
    val homeHalfScore: Long,
    val homeMatchScore: Long,
    val awayHalfScore: Long,
    val awayMatchScore: Long,
    val matchSt: Int,
    val matchAbbr: String
): Parcelable
