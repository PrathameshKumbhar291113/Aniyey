package com.prathameshkumbhar.aniyey.features.anime_list.domain.repository

import androidx.paging.PagingData
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AnimeListRepository {
    fun getAnimeList(): Flow<PagingData<GetAnimeListResponse.Data>>
}