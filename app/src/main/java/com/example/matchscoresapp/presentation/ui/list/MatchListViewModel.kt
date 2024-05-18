package com.example.matchscoresapp.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchscoresapp.core.LeagueUIState
import com.example.matchscoresapp.core.Resource
import com.example.matchscoresapp.data.model.mapper.toLeague
import com.example.matchscoresapp.data.model.mapper.toMatchList
import com.example.matchscoresapp.domain.model.League
import com.example.matchscoresapp.domain.useCase.GetMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchListViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase
) : ViewModel() {

    private val _matchesState = MutableLiveData<LeagueUIState>()
    val matchesState: LiveData<LeagueUIState> = _matchesState

    private val itemList = mutableListOf<League>()

    init {
        _matchesState.value = LeagueUIState.Loading(true)
    }

    fun getMatches() {
        viewModelScope.launch {
            getMatchesUseCase.execute().collect { it ->
                when (it) {
                    is Resource.Error -> {
                        _matchesState.value = LeagueUIState.Error(it.errorMessage ?: "Error")
                    }
                    is Resource.Loading -> {
                        _matchesState.value = LeagueUIState.Loading(true)
                    }
                    is Resource.Success -> {
                        it.data?.let { baseApiResponse ->
                            itemList.clear()
                            baseApiResponse.data?.filter { it.score?.st == 5 }
                                ?.groupBy { it.tournament }?.map { tournamentMap ->
                                    tournamentMap.key?.let { tournament ->
                                        itemList.add(
                                            League(
                                                league = tournament.toLeague(),
                                                list = tournamentMap.value.toMatchList().sortedBy { it.date }
                                            )
                                        )
                                    }
                                }
                            _matchesState.value = LeagueUIState.LeagueList(itemList)
                        }
                    }
                }
            }
        }
    }
}