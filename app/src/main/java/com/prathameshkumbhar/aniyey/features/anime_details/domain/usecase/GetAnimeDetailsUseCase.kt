package com.prathameshkumbhar.aniyey.features.anime_details.domain.usecase

import com.prathameshkumbhar.aniyey.connection.models.GetAnimeDetailsByIdResponse
import com.prathameshkumbhar.aniyey.features.anime_details.domain.repository.AnimeDetailsRepository
import com.prathameshkumbhar.aniyey.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(
    private val animeDetailsRepository: AnimeDetailsRepository
) {
    operator fun invoke(animeId: Int): Flow<NetworkResult<Response<GetAnimeDetailsByIdResponse>>> =
        flow<NetworkResult<Response<GetAnimeDetailsByIdResponse>>> {
            emit(NetworkResult.Loading())
            emit(NetworkResult.Success(data = animeDetailsRepository.getAnimeDetailsById(animeId)))
        }.catch {
            emit(NetworkResult.Error(it.toString()))
        }.flowOn(Dispatchers.IO)
}