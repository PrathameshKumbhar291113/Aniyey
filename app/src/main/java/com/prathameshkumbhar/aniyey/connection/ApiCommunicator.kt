package com.prathameshkumbhar.aniyey.connection

import com.prathameshkumbhar.aniyey.connection.models.GetAnimeDetailsByIdResponse
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import com.prathameshkumbhar.aniyey.utils.NetworkConstants.GET_ANIME_DETAILS_BY_ID
import com.prathameshkumbhar.aniyey.utils.NetworkConstants.GET_ANIME_LIST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCommunicator {

    @GET(GET_ANIME_LIST)
    suspend fun getAnimeList(
        @Query("page") page: Int
    ): Response<GetAnimeListResponse>

    @GET(GET_ANIME_DETAILS_BY_ID)
    suspend fun getAnimeDetailsById(
        @Path("anime_id") animeId: Int
    ): Response<GetAnimeDetailsByIdResponse>
}