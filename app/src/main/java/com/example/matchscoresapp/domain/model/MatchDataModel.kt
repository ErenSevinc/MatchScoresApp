package com.example.matchscoresapp.domain.model

data class League(
    val league: LeagueItem,
    val list: List<Match>
)
data class LeagueItem(
    val id: Long,
    val name: String,
    val url: String
)
data class Match(
    val id: Long,
    val date: Long,
    val homeName: String,
    val awayName: String,
    val homeHalfScore: Long,
    val homeMatchScore: Long,
    val awayHalfScore: Long,
    val awayMatchScore: Long,
    val matchSt: Int,
    val matchAbbr: String
)
