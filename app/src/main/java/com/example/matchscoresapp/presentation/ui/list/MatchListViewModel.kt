package com.example.matchscoresapp.presentation.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _matches = MutableLiveData<MutableList<League>>()
    val matches: LiveData<MutableList<League>> = _matches

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val itemList = mutableListOf<League>()

    fun getMatches() {
        viewModelScope.launch {
            getMatchesUseCase.execute().collect { it ->
                when (it) {
                    is Resource.Error -> {
                        _error.value = it.errorMessage
                        _isLoading.value = false
                    }
                    is Resource.Loading -> {
                        _isLoading.value = true
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
                            _matches.value = itemList
                        }
                    }
                }
            }
        }
    }
}