package com.example.matchscoresapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchscoresapp.core.Resource
import com.example.matchscoresapp.core.base.BaseApiResponse
import com.example.matchscoresapp.data.model.MatchesResponseModel
import com.example.matchscoresapp.domain.useCase.GetMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMatchesUseCase: GetMatchesUseCase
): ViewModel() {

    private val _matches = MutableLiveData<BaseApiResponse<List<MatchesResponseModel>>?>()
    val matches : LiveData<BaseApiResponse<List<MatchesResponseModel>>?> = _matches

    private val _isLoading =MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error =MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        getMatches()
    }

    private fun getMatches() {
        viewModelScope.launch {
            getMatchesUseCase.execute().collect {
                when(it) {
                    is Resource.Error -> {
                        _error.value = it.errorMessage
                        _isLoading.value = false
                    }
                    is Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _matches.value = it.data
                        _isLoading.value = false
                    }
                }
            }
        }
    }
}