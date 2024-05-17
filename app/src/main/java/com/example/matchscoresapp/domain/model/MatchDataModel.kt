package com.example.matchscoresapp.domain.model

import com.example.matchscoresapp.data.model.Tournament

data class MatchesDataModel(
    val tournamentOfMatches: List<League>
)

data class League(
    val id: Long,
    val name: String,
    val url: String,
    val matchList: List<Match>
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
