package com.example.matchscoresapp.data.model.mapper

import com.example.matchscoresapp.data.model.MatchesResponseModel
import com.example.matchscoresapp.data.model.Tournament
import com.example.matchscoresapp.domain.model.LeagueItem
import com.example.matchscoresapp.domain.model.Match

fun MatchesResponseModel.toMatch(): Match {
    return Match(
        matchId = this.matchId ?: -1,
        date = this.date ?: -1,
        homeName = this.homeTeam?.teamName ?: "",
        homeSubName = this.homeTeam?.teamSubName ?: "",
        awayName = this.awayTeam?.teamName ?: "",
        awaySubName = this.awayTeam?.teamSubName ?: "",
        homeMatchScore = this.score?.homeTeam?.teamScore ?: -1,
        awayMatchScore = this.score?.awayTeam?.teamScore ?: -1,
        homeHalfScore = this.score?.homeTeam?.halfTimeScore ?: -1,
        awayHalfScore = this.score?.awayTeam?.halfTimeScore ?: -1,
        matchSt = this.score?.st ?: -1,
        matchAbbr = this.score?.abbr ?: ""

    )
}

fun List<MatchesResponseModel>.toMatchList(): List<Match> {
    return this.map {
        it.toMatch()
    }
}

fun Tournament.toLeague(): LeagueItem {
    return LeagueItem(
        id = this.tournamentId ?: -1,
        name = this.tournamentName ?: "",
        url = this.iconUrl ?: ""
    )
}