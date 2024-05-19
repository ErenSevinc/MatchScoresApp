package com.example.matchscoresapp.data.local

import com.example.matchscoresapp.domain.model.Match
import javax.inject.Inject

class LocalRepository  @Inject constructor(
    private val dao: MatchDao
) {
    fun insert(match: Match) = dao.insert(match)
    fun delete(match: Match) = dao.delete(match)
    fun getFavMatches(): List<Match> = dao.getFavMatch()

}