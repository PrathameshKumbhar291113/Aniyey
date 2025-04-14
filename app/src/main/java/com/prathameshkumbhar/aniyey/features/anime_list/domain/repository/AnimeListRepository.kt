package com.prathameshkumbhar.aniyey.features.anime_list.domain.repository

import androidx.paging.PagingData
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import kotlinx.coroutines.flow.Flow

interface AnimeListRepository {
    fun getAnimeList( sortDescending: Boolean): Flow<PagingData<GetAnimeListResponse.Data>>
}