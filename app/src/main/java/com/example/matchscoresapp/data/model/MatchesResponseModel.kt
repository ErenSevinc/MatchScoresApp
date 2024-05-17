package com.example.matchscoresapp.data.model

import com.google.gson.annotations.SerializedName


data class MatchesResponseModel(
    @SerializedName("i") val matchId: Long?,
    @SerializedName("sgi") val sgi: Long?,
    @SerializedName("d") val date: Long?,
    @SerializedName("st") val sportType: String?,
    @SerializedName("bri") val reportId: Long?,
    @SerializedName("v") val v: String?,
    @SerializedName("str") val str: Boolean?,
    @SerializedName("ht") val homeTeam: TeamInfo?,
    @SerializedName("at") val awayTeam: TeamInfo?,
    @SerializedName("sc") val score: Score?,
    @SerializedName("to") val tournament: Tournament?,
    @SerializedName("pe") val pe: Pe?
)

data class TeamInfo(
    @SerializedName("i") val teamId: Long?,
    @SerializedName("n") val teamName: String?,
    @SerializedName("p") val teamPoint: Long?,
    @SerializedName("sn") val teamSubName: String?,
    @SerializedName("rc") val teamRedCard: Int?
)
data class Score(
    @SerializedName("st") val st: Int?,
    @SerializedName("abbr") val abbr: String?,
    @SerializedName("min") val min: Long?,
    @SerializedName("ht") val ht: TeamScore?,
    @SerializedName("at")val at: TeamScore?,
)
data class Tournament(
    @SerializedName("i") val tournamentId: Long?,
    @SerializedName("n") val tournamentName: String?,
    @SerializedName("sn") val tournamentSubName: String?,
    @SerializedName("p") val tournamentPoint: Long?,
    @SerializedName("flag") val iconUrl: String?
)
data class Pe(
    @SerializedName("pi") val pi: String?,
    @SerializedName("si") val si: String?
)
data class TeamScore(
    @SerializedName("r") val teamScore: Long,
    @SerializedName("c") val teamGoalCount: Long,
    @SerializedName("ht") val halfTimeScore: Long?
)