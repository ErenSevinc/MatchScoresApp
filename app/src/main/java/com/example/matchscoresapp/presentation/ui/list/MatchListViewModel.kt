package com.example.matchscoresapp.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchscoresapp.core.Constants
import com.example.matchscoresapp.core.LeagueUIState
import com.example.matchscoresapp.core.Resource
import com.example.matchscoresapp.data.local.LocalRepository
import com.example.matchscoresapp.data.model.mapper.toLeague
import com.example.matchscoresapp.data.model.mapper.toMatchList
import com.example.matchscoresapp.domain.model.League
import com.example.matchscoresapp.domain.model.LeagueItem
import com.example.matchscoresapp.domain.model.Match
import com.example.matchscoresapp.domain.useCase.GetMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchListViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val getMatchesUseCase: GetMatchesUseCase
) : ViewModel() {

    private val _matchesState = MutableLiveData<LeagueUIState>()
    val matchesState: LiveData<LeagueUIState> = _matchesState

    private val _favMatches = MutableLiveData(emptyList<Match>())

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
                        itemList.clear()
                        // if favourite matches not empty add League row of selected favourite matches
                        if (_favMatches.value?.isNotEmpty() == true) {
                            itemList.add(
                                League(
                                    league = LeagueItem(
                                        id = Constants.FAV_HEADER_ID,
                                        name = Constants.FAV_HEADER_NAME,
                                        subName = Constants.FAV_HEADER_SUB_NAME,
                                        url = Constants.FAV_HEADER_ICON_URL
                                    ),
                                    list = _favMatches.value ?: emptyList()
                                )
                            )
                        }
                        it.data?.let { baseApiResponse ->
                            // filters API response according to finished matches
                            // than groups the finished matches according to league name
                            // finally maps API response model by data model
                            baseApiResponse.data?.filter { it.score?.st == 5 }?.groupBy { it.tournament }?.map { map ->
                                //set first favourite value or the found of favourite items fav value
                                val listMappedMatch = mutableListOf<Match>()
                                map.value.toMatchList().forEach {mapValueItem ->
                                    // sets first favourite value
                                    if (_favMatches.value?.isEmpty() == true) {
                                        mapValueItem.isFavourite = false
                                    } else {
                                        //sets favourite value the found favourite item
                                        _favMatches.value?.forEach {favMatchItem ->
                                            if (favMatchItem.matchId == mapValueItem.matchId) {
                                                mapValueItem.isFavourite = true
                                            }
                                        }
                                    }
                                    //the item add data model list
                                    listMappedMatch.add(mapValueItem)
                                }
                                map.key?.let { tournament ->
                                    itemList.add(
                                        League(
                                            league = tournament.toLeague(),
                                            list = listMappedMatch.sortedBy { it.date }
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

    fun insertMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            //check for selected match is before to be added
            val filteredArticles = _favMatches.value?.firstOrNull {
                it.matchId == match.matchId
            }
            if (filteredArticles == null) {
                match.isFavourite = true
                localRepository.insert(match)
            }
            getFavMatches()
            getMatches()
        }
    }

    fun deleteMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            //find selected match to be deleted
            _favMatches.value?.forEach {
                if (it.matchId == match.matchId) {
                    match.isFavourite = false
                    localRepository.delete(it)
                }
            }
            getFavMatches()
            getMatches()
        }
    }

    private fun getFavMatches() {
        viewModelScope.launch(Dispatchers.IO) {
            _favMatches.postValue(localRepository.getFavMatches())
        }
    }
}