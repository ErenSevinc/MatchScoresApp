package com.example.matchscoresapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.matchscoresapp.core.Constants
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
    val subName: String,
    val url: String? = null
): Parcelable

@Parcelize
@Entity(tableName = Constants.DB_NAME)
data class Match(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val matchId: Long,
    val date: Long,
    val leagueName: String,
    val leagueSubName: String,
    val homeName: String,
    val homeSubName: String,
    val awayName: String,
    val awaySubName: String,
    val homeHalfScore: Long,
    val homeMatchScore: Long,
    val awayHalfScore: Long,
    val awayMatchScore: Long,
    val matchSt: Int,
    val matchAbbr: String,
    var isFavourite: Boolean = false
): Parcelable
