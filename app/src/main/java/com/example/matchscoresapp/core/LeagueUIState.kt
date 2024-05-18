package com.example.matchscoresapp.core

import com.example.matchscoresapp.domain.model.League

sealed class LeagueUIState {
    data class Loading(val isLoading: Boolean): LeagueUIState()
    data class Error(val errorMessage: String): LeagueUIState()
    data class LeagueList (val data: List<League>): LeagueUIState()
}
