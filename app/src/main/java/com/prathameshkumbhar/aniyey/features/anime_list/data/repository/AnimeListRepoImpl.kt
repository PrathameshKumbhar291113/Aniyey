package com.prathameshkumbhar.aniyey.features.anime_list.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prathameshkumbhar.aniyey.connection.ApiCommunicator
import com.prathameshkumbhar.aniyey.connection.models.GetAnimeListResponse
import com.prathameshkumbhar.aniyey.features.anime_list.domain.repository.AnimeListRepository
import com.prathameshkumbhar.aniyey.paging.AnimeListPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeListRepoImpl @Inject constructor(
    private val apiCommunicator: ApiCommunicator
) : AnimeListRepository {

    override fun getAnimeList(): Flow<PagingData<GetAnimeListResponse.Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 25,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnimeListPagingSource(apiCommunicator) }
        ).flow
    }
}


