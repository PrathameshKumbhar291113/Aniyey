package com.prathameshkumbhar.aniyey.features.anime_details.domain.repository

import com.prathameshkumbhar.aniyey.connection.models.GetAnimeDetailsByIdResponse
import retrofit2.Response

interface AnimeDetailsRepository {

    suspend fun getAnimeDetailsById(
        animeId: Int
    ): Response<GetAnimeDetailsByIdResponse>
}