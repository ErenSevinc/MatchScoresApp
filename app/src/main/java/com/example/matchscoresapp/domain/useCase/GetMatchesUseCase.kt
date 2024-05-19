package com.example.matchscoresapp.domain.useCase

import com.example.matchscoresapp.core.Resource
import com.example.matchscoresapp.core.base.BaseApiResponse
import com.example.matchscoresapp.data.model.MatchesResponseModel
import com.example.matchscoresapp.domain.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(private val apiRepository: ApiRepository) {

    fun execute() : Flow<Resource<BaseApiResponse<List<MatchesResponseModel>>>> = flow {
        try {
            emit(Resource.Loading)
            val response = apiRepository.getMatches()
            if (response.success == true) {
                emit(Resource.Success(data = response))
            } else {
                var errors = ""
                response.error?.forEach { errorList ->
                    errorList.forEach {
                        errors += "$it "
                    }
                }
                emit(Resource.Error(errors))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)
}