package com.example.matchscoresapp.data.repository


import com.example.matchscoresapp.core.base.BaseApiResponse
import com.example.matchscoresapp.data.model.MatchesResponseModel
import com.example.matchscoresapp.data.remote.ApiService
import com.example.matchscoresapp.domain.repository.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService): ApiRepository {
    override suspend fun getMatches(): BaseApiResponse<List<MatchesResponseModel>> {
        return apiService.getMatches()
    }
}