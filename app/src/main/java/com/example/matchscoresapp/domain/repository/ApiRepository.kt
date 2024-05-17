package com.example.matchscoresapp.domain.repository

import com.example.matchscoresapp.core.base.BaseApiResponse
import com.example.matchscoresapp.data.model.MatchesResponseModel
import java.util.concurrent.Flow

interface ApiRepository {
    suspend fun getMatches(): BaseApiResponse<List<MatchesResponseModel>>
}