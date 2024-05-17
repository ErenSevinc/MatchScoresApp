package com.example.matchscoresapp.data.remote

import com.example.matchscoresapp.core.base.BaseApiResponse
import com.example.matchscoresapp.data.model.MatchesResponseModel
import retrofit2.http.GET
import java.util.concurrent.Flow

interface ApiService {
    @GET("statistics/sport/SOCCER/matches")
    suspend fun getMatches(): BaseApiResponse<List<MatchesResponseModel>>
}