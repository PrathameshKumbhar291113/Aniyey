package com.prathameshkumbhar.aniyey.features.anime_details.data.repository

import com.prathameshkumbhar.aniyey.connection.ApiCommunicator
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeDetailsByIdResponse
import com.prathameshkumbhar.aniyey.features.anime_details.domain.repository.AnimeDetailsRepository
import retrofit2.Response
import javax.inject.Inject

class AnimeDetailsRepoImpl @Inject constructor(
    private val apiCommunicator: ApiCommunicator
) : AnimeDetailsRepository {
    override suspend fun getAnimeDetailsById(animeId: Int): Response<GetAnimeDetailsByIdResponse> {
        return apiCommunicator.getAnimeDetailsById(animeId)
    }

}
